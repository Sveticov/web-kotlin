package com.svetikov.webkotlin.repository

import com.sun.el.stream.Optional
import com.svetikov.webkotlin.model.Address
import com.svetikov.webkotlin.model.Consumer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException


@Repository("consumer")
class ConsumerRepository : CommonRepository<Consumer, Long> {

    private val dataConsumerTest = mutableListOf(
        Consumer(1, "f1", "s1",Address(1, "1@mail.com", "Norway"))
//        Consumer(2, "f2", "s2"),// Address(2, "2@mail.com", "Norway")),
//        Consumer(3, "f3", "s3"),// Address(3, "3@mail.com", "Sweden")),
//        Consumer(5, "f5", "s5"// Address(5, "5@mail.com", "Sweden")),
    )

    override fun findAll(): Collection<Consumer> {
        return dataConsumerTest.toList()
    }

    override fun save(body: Consumer): Consumer {

        if(dataConsumerTest.any { it.id ==body.id }){
            throw IllegalArgumentException("Consumer with id $body already exist")
        }
        dataConsumerTest.add(body)
        return body
    }

    override fun findByID(id: Long): Consumer {
        return  dataConsumerTest.firstOrNull(){it.id == id}?:
        throw  NoSuchElementException("This id $id could be found")

//        return dataConsumerTest
//            .filter { consumer -> consumer.id == id }
//            .first()

    }

    override fun deleteByID(id: Long): Consumer {
        val consumer = findByID(id)
     val status=   dataConsumerTest.remove(consumer)
        println("delete $status")
        return consumer
    }
}