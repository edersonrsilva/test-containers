package br.com.edrs.tcrabbitmq.service

import br.com.edrs.tcrabbitmq.domain.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.RabbitMQContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class PersonServiceTest(
    private val personService: PersonService,
    private val rabbitTemplate: RabbitTemplate
) {

    @Value("\${spring.rabbitmq.bindings.send-notification.queueName}")
    private lateinit var queue: String

    companion object {
        @Container
        val container = RabbitMQContainer(
            DockerImageName.parse("rabbitmq:3-management")
        )

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.rabbitmq.host", container::getHost)
            registry.add("spring.rabbitmq.port", container::getAmqpPort)
            registry.add("spring.rabbitmq.username", container::getAdminUsername)
            registry.add("spring.rabbitmq.password", container::getAdminPassword)
        }
    }

    @Test
    fun `should produce and consume a same person`() {
        val producedPerson = personService.produce()

        val consumedPerson = rabbitTemplate.receiveAndConvert(queue, 5000) as Person

        assertNotNull(producedPerson)
        assertNotNull(consumedPerson)
        assertEquals(producedPerson.name, consumedPerson.name)
        assertEquals(producedPerson.surname, consumedPerson.surname)
        assertEquals(producedPerson.age, consumedPerson.age)
        assertEquals(producedPerson.email, consumedPerson.email)
    }

    @Test
    fun `should consume 20 messages`() {
        val messages = mutableListOf<Person>()

        repeat(20) {
            personService.produce()
            messages.add(rabbitTemplate.receiveAndConvert(queue, 5000) as Person)
        }

        assertEquals(20, messages.size)
    }

}