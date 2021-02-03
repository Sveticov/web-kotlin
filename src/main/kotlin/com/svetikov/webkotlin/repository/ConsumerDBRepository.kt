package com.svetikov.webkotlin.repository

import com.svetikov.webkotlin.model.AllConsumer
import com.svetikov.webkotlin.model.AllConsumerImpl
import com.svetikov.webkotlin.model.Consumer
import com.svetikov.webkotlin.model.ConsumerProducts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Repository("consumer_db_i")
interface ConsumerDBRepository : JpaRepository<Consumer, Long> {

    @Query("select c.first_name from consumer c where c.id = ?1", nativeQuery = true)
    fun selectFirstName(id: Long): String

//    @Query("select * from consumer c  left join address a on c.address_id=a.id", nativeQuery = true)
//    override fun findAll(): MutableList<Consumer>

    @Query(
        " select c.id,c.first_name,c.second_name,a.email,a.country " +
                "from consumer c inner join address a on c.address_id=a.id ;",
        nativeQuery = true
    )
    fun findAllConsumerAddress(): Collection<AllConsumer>


    @Query("select c.id,c.first_name,c.second_name,p.id as id_p, p.name_product,p.description_product,cs.consumer_id " +
            "from consumer c inner join consumer_products cs on c.id=cs.consumer_id inner join products p on p.id=cs.products_id order by c.id ",
        nativeQuery = true
    )
    fun findAllConsumerAndProducts():Collection<ConsumerProducts>


}