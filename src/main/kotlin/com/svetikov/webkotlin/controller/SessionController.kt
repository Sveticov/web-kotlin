package com.svetikov.webkotlin.controller

import com.svetikov.webkotlin.sesion.WebSessionBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.lang.NullPointerException

@RestController
class SessionController(@Autowired private val session:WebSessionBean) {

    @ExceptionHandler(NullPointerException::class)
    fun nullPointerExceptionMessage(e:NullPointerException):ResponseEntity<String> =
        ResponseEntity(e.message,HttpStatus.BAD_GATEWAY)

    @GetMapping("/api/session")
    fun exampleSession( name:String):String{

            return when {

                name.isNotEmpty() -> {
                    session.name=name
                    "name has been $name"
                }
                session.name.isNotEmpty() -> {
                    "Current name (${session.name})"
                }
                else -> "No name"
            }
        }



}