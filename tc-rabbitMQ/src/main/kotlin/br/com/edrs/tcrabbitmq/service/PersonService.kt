package br.com.edrs.tcrabbitmq.service

import br.com.edrs.tcrabbitmq.factory.PersonFactory
import br.com.edrs.tcrabbitmq.person.Person
import com.pedrocomitto.rabbit4lazy.GenericProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val genericProducer: GenericProducer
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Value("\${spring.rabbitmq.bindings.send-notification.queueName}")
    private lateinit var queue: String

    fun produce(): Person {
        val person = PersonFactory.build()
        log.info("M=produce, I=Produce person=$person")
        genericProducer.produce("send-notification", person)

        return person
    }

}