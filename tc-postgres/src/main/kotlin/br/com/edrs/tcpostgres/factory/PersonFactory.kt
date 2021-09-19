package br.com.edrs.tcpostgres.factory

import br.com.edrs.tcpostgres.domain.Person

object PersonFactory {

    fun build() = Person(
        name = faker.witcher().character(),
        surname = faker.name().lastName(),
        age = faker.number().numberBetween(1, 99),
        email = faker.internet().safeEmailAddress()
    )

}