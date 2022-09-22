package com.ftchinese.api.routes

import com.ftchinese.api.dao.dao
import com.ftchinese.api.models.errNotFound
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.storyByIdRoute() {
    get("/stories/{id}") {
        val id = call.parameters["id"]
        if (id == null) {
            call.respond(errNotFound())
            return@get
        }

        val story = dao.story(id)
        if (story == null) {
            call.respond(errNotFound())
            return@get
        }
        call.respond(story)
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