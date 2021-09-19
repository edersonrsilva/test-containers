package br.com.edrs.tcpostgres.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController {

    @GetMapping
    fun getIndex() = "TC-POSTGRES"

}