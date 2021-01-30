package com.svetikov.webkotlin.sesion

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.SessionScope

@SessionScope
@Component
data class WebSessionBean(var name: String ="") {
}