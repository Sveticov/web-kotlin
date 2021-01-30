package com.svetikov.webkotlin.model

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat
data class AllConsumer(
    var id: Long=0,
    var first_name1: String="",
    var second_name: String="",
    var email: String="",
    var country: String=""
) {
}