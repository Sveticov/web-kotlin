package com.svetikov.webkotlin.service

import com.svetikov.webkotlin.model.*
import com.svetikov.webkotlin.repository.ConsumerDBRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@Qualifier("consumer_service")
class ConsumerService(@Qualifier("consumer_db_i") private val repository: ConsumerDBRepository) :
    CommonService<Consumer, Long> {

    override fun findAll(): Collection<Consumer> {
        return repository.findAll()
    }

    override fun save(body: Consumer): Consumer {

        return repository.save(body)
    }

    override fun findByID(id: Long): Consumer {
        return repository.findById(id).get()
    }

    override fun deleteByID(id: Long): Consumer {
        val consumer = findByID(id)
        repository.deleteById(id)
        return consumer
    }

    override fun selectName(id: Long): String =
        repository.selectFirstName(id)

    override fun selectEmail(id: Long): String =
        repository.findById(id).map { consumer -> consumer.address }.map { address -> address?.email }.get()

    override fun findAllConsumerAddress(): Collection<AllConsumerImpl> {
        val all: Collection<AllConsumer> = repository.findAllConsumerAddress()

        return all.map {
            AllConsumerImpl(
                it.id,
                it.first_name,
                it.second_name,
                it.email,
                it.country
            )
        } as MutableList<AllConsumerImpl>
    }

    override fun findAllConsumerAndProducts(): Collection<Consumer> {
//        val consumer = mutableListOf<Consumer>()
//        val products = mutableListOf<Products>()
//
//        for (c in repository.findAllConsumerAndProducts()) {
//            if (c.consumer_id==c.id)
//            products.add(Products(c.id_p,c.name_product,c.description_product))
//
//            consumer.add(Consumer(c.id,c.first_name,c.second_name,null,products))
//        }
//return consumer
        return repository.findAllConsumerAndProducts().map {
            Consumer(
                it.id, it.first_name, it.second_name, null,
                setOf(Products(it.id_p, it.name_product, it.description_product/*, emptySet()*/))
            )
        }.toList()
    }


}