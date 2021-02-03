package com.svetikov.webkotlin.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity
@Table(name = "consumer")
data class Consumer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val firstName: String,
    val secondName: String,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    val address: Address? = null,
    @ManyToMany( cascade = [CascadeType.ALL],fetch = FetchType.EAGER)
    @JoinTable(name = "consumer_products",joinColumns = [JoinColumn(name="consumer_id",referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name="products_id",referencedColumnName = "id")])

   // @JsonManagedReference
    val products:Set<Products> = mutableSetOf<Products>()
) {

}