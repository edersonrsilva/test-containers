package br.com.edrs.tcpostgres.domain

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "persons")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_person")
    var id: Long = 0,

    @Column(name = "ds_name")
    val name: String,

    @Column(name = "ds_surname")
    val surname: String,

    @Column(name = "nr_age")
    val age: Int,

    @Column(name = "ds_email")
    val email: String,

    @CreationTimestamp
    @Column(name = "dt_creation")
    val dateCreation: LocalDateTime = LocalDateTime.now()

)