package br.com.edrs.tckafka.consumer

import br.com.edrs.tckafka.domain.Person
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class PersonConsumer {

    private val log = LoggerFactory.getLogger(javaClass)
    private var person: Person? = null

    @KafkaListener(topics = ["\${spring.kafka.topic.new-person}"])
    fun consume(person: Person) {
        log.info("M=consume, I=Data received=${person}")
        set(person)
    }

    private fun set(person: Person) {
        this.person = person
    }

    fun get() = person
}