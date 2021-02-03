package com.svetikov.webkotlin.repository

import com.svetikov.webkotlin.model.Consumer_Products
import com.svetikov.webkotlin.model.Products
import com.svetikov.webkotlin.model.Products_Consumer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository("products")
interface ProductsRepository : JpaRepository<Products, Long> {

    @Query(value = "select*from consumer_products", nativeQuery = true)
    fun showAllCon_Prod(): Collection<Consumer_Products>

    @Query(
        value =
        "select p.id,p.name_product,p.description_product,c.first_name,c.second_name" +
                " from products p inner join consumer_products cp on p.id=cp.products_id inner join consumer c on c.id=cp.consumer_id where cp.products_id = ?1",
        nativeQuery = true
    )
    fun showProductsConsumer(products_id: Long): Collection<Products_Consumer>

    @Query()
    fun saveConsumerProducts(id_consumer:Long,id_products:Long)
}