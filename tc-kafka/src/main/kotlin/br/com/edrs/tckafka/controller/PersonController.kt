package br.com.edrs.tckafka.controller

import br.com.edrs.tckafka.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("person")
class PersonController(
    private val personService: PersonService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create() = personService.produce()

}