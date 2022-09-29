package com.example.pages

import com.example.models.Account
import kotlinx.html.*

fun HTML.articleLayout(
    title: String,
    account: Account? = null,
    block: DIV.() -> Unit
) {
    baseLayout(title) {
        nav(
            classes = "navbar bg-light"
        ) {
            div(classes = "container") {
                div(classes = "hstack") {
                    a( href = "/", classes = "navbar-brand") {
                        img(src = "/static/ktor_logo.png")
                        +"Kotlin Ktor Journal"
                    }

                    div(classes = "ms-auto") {
                        if (account != null) {
                            span { +account.displayName }
                            a(href = "/auth/logout") {
                                +"Logout"
                            }
                        }

                    }
                }
            }
        }

        div(classes = "container") {
            div(classes = "row justify-content-center") {
                div(classes = "col-md-8 col-lg-6", block = block)
            }
        }

        footer(classes = "border-top mt-3") {
            div(classes = "container") {
                div(classes = "row") {
                    p(classes = "text-center") {
                        i { +"Powered by Ktor & HTML DSL!" }
                    }
                }
            }
        }
    }
}