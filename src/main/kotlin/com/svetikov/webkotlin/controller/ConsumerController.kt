package com.svetikov.webkotlin.controller


import com.svetikov.webkotlin.model.Address
import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.AllConsumerImpl
import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.service.CommonService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.web.servlet.server.Session
import org.springframework.boot.web.servlet.server.Session.Cookie
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap

import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.net.http.HttpHeaders
import java.net.http.HttpResponse
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/api/consumer")
class ConsumerController(@Qualifier("consumer_service") private val service: CommonService<Consumer, Long>) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun emptyResultDataAccessException(e:EmptyResultDataAccessException):ResponseEntity<String> =
        ResponseEntity(e.message,HttpStatus.NOT_FOUND)

    @RequestMapping(value = ["/settings"], method = [RequestMethod.OPTIONS])
    fun setting(): ResponseEntity<*> {
        val headers = LinkedMultiValueMap<String, String>()
        headers.add("Allow", "GET,DELETE,POST")

        return ResponseEntity("i'm ok", headers, HttpStatus.OK)
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIDConsumer(@PathVariable id: Long): Consumer =
        service.findByID(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun findAllConsumer(): Collection<Consumer> {

        return service.findAll()
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveConsumer(@RequestBody consumer: Consumer, response: HttpServletResponse): ResponseEntity<Consumer> {
        val cookie = javax.servlet.http.Cookie("data", consumer.firstName)
        cookie.isHttpOnly=true
        cookie.maxAge = 10
        response.addCookie(cookie)
        return ResponseEntity(service.save(consumer), HttpStatus.CREATED)
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteConsumer(@PathVariable id: Long): Consumer =
        service.deleteByID(id)


    @GetMapping("c/{id}")
    fun readCookie(@PathVariable id:Long,http:HttpServletResponse) :String {

         val cookie=javax.servlet.http.Cookie("t", service.findByID(id).toString())
        http.addCookie(cookie)
        return   service.findByID(id).firstName
    }

    @GetMapping("/name/{id}")
    fun selectName(@PathVariable id:Long):ResponseEntity<String> =
        ResponseEntity(service.selectName(id),HttpStatus.OK)


    @GetMapping("/email/{id}")
    fun selectEmail(@PathVariable id: Long): String=
        service.selectEmail(id)

    @GetMapping("/test")
    fun test(){
        val address1= Address(0,"first@gmail.com","Norway")
        val consumer1=Consumer(0,"fname","sname",address1)
        service.save(consumer1)
    }
    @GetMapping("/full")
    fun findAllConsumerAddress(): ResponseEntity< Collection<AllConsumerImpl>> =
        ResponseEntity(service.findAllConsumerAddress(),HttpStatus.OK)



}