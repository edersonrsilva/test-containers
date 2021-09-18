package br.com.edrs.tckafka.producer

import br.com.edrs.tckafka.domain.Person
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PersonProducer(
    private val kafkaTemplate: KafkaTemplate<String, Person>
) {

    @Value("\${spring.kafka.topic.new-person}")
    private lateinit var topic: String

    fun send(person: Person) {
        kafkaTemplate.send(topic, person)
    }

}