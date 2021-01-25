package com.svetikov.webkotlin.service

import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.repository.CommonRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
@Qualifier("consumer_service")
class ConsumerService(@Qualifier("consumer")private val repository: CommonRepository<Consumer,Long>):CommonService<Consumer,Long> {

    override fun findAll(): Collection<Consumer> {
     return  repository.findAll()
    }

    override fun save(body: Consumer): Consumer {

     return repository.save(body)
    }

    override fun findByID(id: Long): Consumer {
        return repository.findByID(id)
    }

    override fun deleteByID(id: Long): Consumer {
        return repository.deleteByID(id)
    }
}