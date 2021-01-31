package com.svetikov.webkotlin.service

import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.AllConsumerImpl
import com.svetikov.webkotlin.model.Consumer

interface CommonService<T,E> {
    fun findAll():Collection<T>

    fun save(body:T):T

    fun findByID(id:E):T

    fun deleteByID(id:E):T

    fun selectName(id:E):String

    fun selectEmail(id: E): String

    fun findAllConsumerAddress(): Collection<AllConsumerImpl>
}