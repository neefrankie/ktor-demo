package com.example.routes

import com.example.dao.dao
import com.example.models.Account
import com.example.models.Credentials
import com.example.pages.forgotPasswordPage
import com.example.pages.loginPage
import com.example.pages.signupPage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
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
                val su = Credentials(
                    email = formParams.getOrFail("email"),
                    password = formParams.getOrFail("password"),
                )
                val errMsg = su.validate()
                if (errMsg.isNotEmpty()) {
                    call.respondHtml(HttpStatusCode.OK) {
                        loginPage(errMsg, su)
                    }
                } else {
                    val account = dao.emailLogin(su.email, su.password)
                    if (account != null) {
                        call.sessions.set(account)
                        call.respondRedirect("/articles")
                    } else {
                        call.respondHtml(HttpStatusCode.OK) {
                            loginPage(
                                value = su,
                                formErr = "Not Found"
                            )
                        }
                    }
                }
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
                val su = Credentials(
                    email = formParams.getOrFail("email"),
                    password = formParams.getOrFail("password"),
                    confirmPassword = formParams.getOrFail("confirmPassword")
                )

                val errMsg = su.validate(true)
                if (errMsg.isEmpty()) {
                    val account = dao.emailSignUp(su.email, su.password)
                    if (account != null) {
                        call.sessions.set(account)
                        call.respondRedirect("/articles")
                    } else {
                        call.respondHtml(HttpStatusCode.OK) {
                            signupPage(
                                value = su,
                                formErr = "Not Found"
                            )
                        }
                    }
                } else {
                    call.respondHtml(HttpStatusCode.OK) {
                        signupPage(errMsg, su)
                    }
                }
            }
        }

        get("forgot-password") {
            call.respondHtml(HttpStatusCode.OK) {
                forgotPasswordPage()
            }
        }

        get("logout") {
            call.sessions.clear<Account>()
            call.respondRedirect("/auth/login")
        }
    }
}