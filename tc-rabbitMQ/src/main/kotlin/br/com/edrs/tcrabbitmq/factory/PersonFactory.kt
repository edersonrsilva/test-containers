package br.com.edrs.tcrabbitmq.factory

import br.com.edrs.tcrabbitmq.domain.Person

object PersonFactory {

    fun build() = Person(
        name = faker.name().firstName(),
        surname = faker.name().lastName(),
        age = faker.number().numberBetween(1, 99),
        email = faker.internet().safeEmailAddress()
    )

}