package com.svetikov.webkotlin.service

import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.repository.CommonRepository
import com.svetikov.webkotlin.repository.ConsumerDBRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.*

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

    override fun findAllConsumerAddress(): Collection<AllConsumer> =
        repository.findAllConsumerAddress()


}