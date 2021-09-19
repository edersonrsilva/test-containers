package br.com.edrs.tcpostgres.service

import br.com.edrs.tcpostgres.factory.PersonFactory
import br.com.edrs.tcpostgres.repository.PersonRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class PersonServiceTest(
    private val personService: PersonService,
    private val personRepository: PersonRepository,
    private val jdbcTemplate: JdbcTemplate
) {

    companion object {
        @Container
        val container = PostgreSQLContainer<Nothing>(
            DockerImageName.parse("postgres:latest")
        )

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.username", container::getUsername)
            registry.add("spring.datasource.password", container::getPassword)
        }
    }

    @AfterEach
    fun cleanup() {
        jdbcTemplate.execute("truncate table persons")
    }

    @Test
    fun `should save a person in database`() {
        val reader = personService.create()

        val savedReader = personRepository.findByIdOrNull(reader.id)

        assertNotNull(reader)
        assertNotNull(savedReader)
        assertEquals(reader.name, savedReader?.name)
        assertEquals(reader.surname, savedReader?.surname)
        assertEquals(reader.age, savedReader?.age)
        assertEquals(reader.email, savedReader?.email)
    }

    @Test
    fun `should find 15 persons`() {
        repeat(15) {
            personRepository.save(PersonFactory.build())
        }

        val persons = personService.findAll()

        assertEquals(15, persons.size)
    }

}