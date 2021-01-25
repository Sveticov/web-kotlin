package com.svetikov.webkotlin.repository

interface CommonRepository<T,E> {
    fun findAll():Collection<T>

    fun save(body:T):T

    fun findByID(id:E):T

    fun deleteByID(id:E):T
}