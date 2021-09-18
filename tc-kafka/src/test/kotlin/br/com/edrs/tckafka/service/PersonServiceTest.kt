package br.com.edrs.tckafka.service

import br.com.edrs.tckafka.consumer.PersonConsumer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class PersonServiceTest(
    private val personService: PersonService,
    private val personConsumer: PersonConsumer
) {

    companion object {
        @Container
        val container = KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:5.4.3")
        )

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.kafka.bootstrap-servers", container::getBootstrapServers)
        }
    }

    @Test
    fun `should produce and consume a same person`() {
        val producedPerson = personService.produce()
        Thread.sleep(1000)
        val consumedPerson = personConsumer.get()

        assertNotNull(producedPerson)
        assertNotNull(consumedPerson)
        assertEquals(producedPerson.name, consumedPerson?.name)
        assertEquals(producedPerson.surname, consumedPerson?.surname)
        assertEquals(producedPerson.age, consumedPerson?.age)
        assertEquals(producedPerson.email, consumedPerson?.email)
    }

}