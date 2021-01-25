package com.svetikov.webkotlin.controller

import com.svetikov.webkotlin.model.Address
import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.service.CommonService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException
import java.net.http.HttpResponse

@RestController
@RequestMapping("/api/consumer")
class ConsumerController(@Qualifier("consumer_service") private val service: CommonService<Consumer, Long>) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @RequestMapping(value = ["/settings"],method = [RequestMethod.OPTIONS])
    fun setting():ResponseEntity<*> {
       val headers = LinkedMultiValueMap<String, String>()
        headers.add("Allow","GET,DELETE,POST")



       return ResponseEntity("i'm ok",headers,HttpStatus.OK)
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIDConsumer(@PathVariable id: Long): Consumer =
        service.findByID(id)

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun findAllConsumer(): Collection<Consumer> =
        service.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveConsumer(@RequestBody consumer: Consumer): Consumer =
        service.save(consumer)

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteConsumer(@PathVariable id: Long): Consumer =
        service.deleteByID(id)


}