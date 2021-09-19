package br.com.edrs.tcpostgres.repository

import br.com.edrs.tcpostgres.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long>