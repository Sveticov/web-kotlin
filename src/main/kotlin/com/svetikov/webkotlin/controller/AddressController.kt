package com.svetikov.webkotlin.controller

import com.svetikov.webkotlin.model.Address
import com.svetikov.webkotlin.service.AddressService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/address")
class AddressController(@Qualifier("service_address")private val service:AddressService) {


    @PostMapping("")
    fun saveAddress(body:Address):ResponseEntity<Address> =
       ResponseEntity( service.save(body),HttpStatus.CREATED)

    @GetMapping("/all")
    fun findAllAddress():ResponseEntity<Collection<Address>> =
       ResponseEntity( service.findAll(),HttpStatus.FOUND)
    @GetMapping("/{id}")
    fun findByIDAddress(@PathVariable id:Long):ResponseEntity<Address> =
        ResponseEntity(service.findByID(id),HttpStatus.FOUND)

    @DeleteMapping("/{id}")
    fun deleteAddress(@PathVariable id:Long):ResponseEntity<Address>  =
        ResponseEntity(service.deleteByID(id),HttpStatus.OK)



}