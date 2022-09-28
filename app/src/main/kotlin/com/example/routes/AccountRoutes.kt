package com.example.routes

import com.example.dao.dao
import com.example.pages.forgotPasswordPage
import com.example.pages.loginPage
import com.example.pages.signupPage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.authRouting() {
    route("/auth") {
        route("login") {
            get {
                call.respondHtml(HttpStatusCode.OK) {
                    loginPage()
                }
            }
            post {
                val formParams = call.receiveParameters()
                val email = formParams.getOrFail("email")
                val password = formParams.getOrFail("password")

                call.respondRedirect("/articles")
            }
        }

        route("signup") {
            get {
                call.respondHtml(HttpStatusCode.OK) {
                    signupPage()
                }
            }

            post {
                val formParams = call.receiveParameters()
                val email = formParams.getOrFail("email")
                val password = formParams.getOrFail("password")
                val account = dao.emailSignUp(email, password)
                call.respondRedirect("/articles")
            }
        }

        get("forgot-password") {
            call.respondHtml(HttpStatusCode.OK) {
                forgotPasswordPage()
            }
        }
    }
}