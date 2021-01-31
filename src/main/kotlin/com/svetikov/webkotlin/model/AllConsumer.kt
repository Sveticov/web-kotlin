package com.svetikov.webkotlin.model

import com.fasterxml.jackson.annotation.JsonFormat


@JsonFormat
interface AllConsumer {
    var id: Long
    var first_name: String
    var second_name: String
    var email: String
    var country: String
}



