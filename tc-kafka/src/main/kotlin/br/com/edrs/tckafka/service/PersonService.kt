package br.com.edrs.tckafka.service

import br.com.edrs.tckafka.domain.Person
import br.com.edrs.tckafka.factory.PersonFactory
import br.com.edrs.tckafka.producer.PersonProducer
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personProducer: PersonProducer
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun produce(): Person {
        val person = PersonFactory.build()
        log.info("M=produce, I=Data sent=$person")
        personProducer.send(person)

        return person
    }

}