package com.example.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.formAuthentication() {
    install(Authentication) {
        form {

        }
    }
}