package com.example.plugins

import com.example.routes.articleRouting
import com.example.routes.authRouting
import com.example.routes.customerRouting
import com.example.routes.orderRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting() {
    routing {
        trace { application.log.trace(it.buildText()) }
        authRouting()
    }
}

fun Application.configureArticleRouting() {
    routing {
        get("/") {
            call.respondRedirect("articles")
        }

        articleRouting()

        static("/static") {
            resources("files")
        }
    }
}

fun Application.configureApiRouting() {
    routing {
        route("/api") {
            customerRouting()
            orderRouting()
        }
    }
}

