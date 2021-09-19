package br.com.edrs.tcpostgres.controller

import br.com.edrs.tcpostgres.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping
    fun findAll() = personService.findAll()

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long) = personService.findById(id)

    @PostMapping
    fun create() = personService.create()

}