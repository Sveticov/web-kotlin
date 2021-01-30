package com.svetikov.webkotlin.service

import com.svetikov.webkotlin.model.Address
import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.repository.AddressDBRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service("service_address")
class AddressService(@Qualifier("repository_address") private val repository:AddressDBRepository ):CommonService<Address,Long> {
    override fun findAll(): Collection<Address> =
        repository.findAll()

    override fun save(body: Address): Address =
        repository.save(body)

    override fun findByID(id: Long): Address =
        repository.findById(id).get()

    override fun deleteByID(id: Long): Address {
       val address = findByID(id)
        repository.deleteById(id)
        return address
    }

    override fun selectName(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun selectEmail(id: Long): String =
        repository.findById(id).get().email

    override fun findAllConsumerAddress(): Collection<AllConsumer> {
        TODO("Not yet implemented")
    }

}