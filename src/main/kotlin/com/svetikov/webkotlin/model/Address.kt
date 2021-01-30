package com.svetikov.webkotlin.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name="address")
data class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long,
    val email:String,
    val country:String,
//    @OneToOne(mappedBy = "address",cascade = [CascadeType.ALL],fetch= FetchType.LAZY)
//    @JsonManagedReference
//val consumer:Consumer?=null
)
