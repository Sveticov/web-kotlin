package com.svetikov.webkotlin.controller

import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.model.Consumer_Products
import com.svetikov.webkotlin.model.Products
import com.svetikov.webkotlin.model.Products_Consumer
import com.svetikov.webkotlin.repository.ProductsRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/products")
class ProductsController(@Qualifier("products") private val repository:ProductsRepository) {

    @GetMapping("/all")
    fun allProducts():Collection<Products> =
        repository.findAll()

    @GetMapping("{id}")
    fun findByID(@PathVariable id:Long):ResponseEntity<Products>{

     return   ResponseEntity(repository.findById(id).get(),HttpStatus.OK)

    }

    @GetMapping("/show")
    fun showAllConsumerProducts():Collection<Consumer_Products> =
        repository.showAllCon_Prod()


    @PostMapping
    fun saveNewProduct(@RequestBody product:Products):ResponseEntity<Products> =
        ResponseEntity(repository.save(product),HttpStatus.CREATED)

    @GetMapping("/show/{id}")
    fun showProductConsumer(@PathVariable id:Long):Collection<Products_Consumer> =
        repository.showProductsConsumer(id)




}