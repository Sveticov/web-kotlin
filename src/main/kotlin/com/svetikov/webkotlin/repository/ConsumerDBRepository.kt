package com.svetikov.webkotlin.repository

import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.AllConsumerImpl
import com.svetikov.webkotlin.model.Consumer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Repository("consumer_db_i")
interface ConsumerDBRepository : JpaRepository<Consumer, Long> {

    @Query("select c.first_name from consumer c where c.id = ?1", nativeQuery = true)
    fun selectFirstName(id: Long): String

    @Query("select * from consumer c  left join address a on c.address_id=a.id", nativeQuery = true)
    override fun findAll(): MutableList<Consumer>

    @Query(
        " select c.id,c.first_name,c.second_name,a.email,a.country " +
                "from consumer c inner join address a on c.address_id=a.id ;",
        nativeQuery = true
    )
    fun findAllConsumerAddress(): Collection<AllConsumer>


}