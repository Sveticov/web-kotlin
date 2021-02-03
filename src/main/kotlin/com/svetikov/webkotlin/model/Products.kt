package com.svetikov.webkotlin.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name="products")
data class Products (
    @Id
//   @GeneratedValue(strategy=GenerationType.AUTO)
    val id:Long,
    val nameProduct:String,
    val descriptionProduct:String,

//    @ManyToMany( mappedBy = "products")
////    @JsonManagedReference
//    val consumer:Set<Consumer> = mutableSetOf()
){
}