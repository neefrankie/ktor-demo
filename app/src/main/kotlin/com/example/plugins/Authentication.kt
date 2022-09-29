package com.example.plugins

import com.example.models.Account
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import io.ktor.util.hex

fun Application.configureAuthentication() {
    val dotEnv = dotenv()
    install(Sessions) {
        val secretEncryptKey = hex(dotEnv["SESSION_ENCRYPT_KEY"])
        val secretSignKey = hex(dotEnv["SESSION_SIGN_KEY"])
        cookie<Account>("ktor_session") {
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }
    install(Authentication) {
        session<Account>("auth-session") {
            validate {
                it
            }
            challenge {
                call.respondRedirect("/auth/login")
            }
        }
    }
}