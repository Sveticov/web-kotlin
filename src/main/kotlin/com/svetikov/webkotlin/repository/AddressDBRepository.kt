package com.svetikov.webkotlin.repository

import com.svetikov.webkotlin.model.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("repository_address")
interface AddressDBRepository: JpaRepository<Address,Long> {
}