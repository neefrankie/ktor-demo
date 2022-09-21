package com.ftchinese.api.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.storyByIdRoute() {
    get("/stories/{id}") {
        val id = call.parameters["id"]

        call.respondText { "Story $id" }
    }
}

fun Route.interactiveByIdRoute() {
    get("/interactive/{id}") {
        call.respondText { "Interactive ${call.parameters["id"]}" }
    }
}

fun Route.videoByIdRoute() {
    get("/videos/{id}") {
        call.respondText { "Video ${call.parameters["id"]}" }
    }
}

fun Route.galleryByIdRoute() {
    get("/galleries/{id}") {
        call.respondText { "Gallery ${call.parameters["id"]}" }
    }
}

fun Application.contentRoutes() {
    routing {
        route("/contents") {
            storyByIdRoute()
            interactiveByIdRoute()
            videoByIdRoute()
            galleryByIdRoute()
        }
    }
}