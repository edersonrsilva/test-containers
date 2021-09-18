package br.com.edrs.tckafka.factory

import br.com.edrs.tckafka.domain.Person

object PersonFactory {

    fun build() = Person(
        name = faker.name().firstName(),
        surname = faker.name().lastName(),
        age = faker.random().nextInt(10, 99),
        email = faker.internet().safeEmailAddress()
    )

}