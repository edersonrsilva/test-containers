package br.com.edrs.tcpostgres.service

import br.com.edrs.tcpostgres.domain.Person
import br.com.edrs.tcpostgres.exception.NotFoundException
import br.com.edrs.tcpostgres.factory.PersonFactory
import br.com.edrs.tcpostgres.repository.PersonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun findAll(): List<Person> = personRepository.findAll()

    fun findById(id: Long): Person = personRepository.findByIdOrNull(id) ?: throw NotFoundException()

    fun create(): Person = personRepository.save(PersonFactory.build())

}