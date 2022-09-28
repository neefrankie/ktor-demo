package com.example.plugins

import com.example.dao.dao
import com.example.pages.*
import com.example.routes.customerRouting
import com.example.routes.orderRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Application.configureAuthRouting() {
    routing {
        trace { application.log.trace(it.buildText()) }
        route("/auth") {
            get("login") {
                call.respondHtml(HttpStatusCode.OK) {
                    loginPage()
                }
            }
            get("signup") {
                call.respondHtml(HttpStatusCode.OK) {
                    signupPage()
                }
            }
            get("forgot-password") {
                call.respondHtml(HttpStatusCode.OK) {
                    forgotPasswordPage()
                }
            }
        }
    }
}

fun Application.configureArticleRouting() {
    routing {
        get("/") {
            call.respondRedirect("articles")
        }
        route("articles") {
            get {
                // Show a list of articles
                val articles = dao.allArticles()
                call.respondHtml {
                    articleListPage(articles)
                }
            }
            get("new") {
                // Show a page with fields for creating a new article
                call.respondHtml(status = HttpStatusCode.OK) {
                    newArticlePage()
                }
            }
            post {
                // save an article
                val formParameters = call.receiveParameters()
                val title = formParameters.getOrFail("title")
                val body = formParameters.getOrFail("body")
                val article = dao.addNewArticle(title, body)
                call.respondRedirect("/articles/${article?.id}")
            }
            get("{id}") {
                // Show an article with a specific id
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val article = dao.article(id)
                call.respondHtml {
                    if (article != null) {
                        articlePage(article)
                    } else {
                        notFoundPage()
                    }
                }
            }
            get("{id}/edit") {
                // Show a page with fields for editing an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val article = dao.article(id)
                call.respondHtml {
                    if (article != null) {
                        articleEditPage(article)
                    } else {
                        notFoundPage()
                    }
                }
            }
            post("{id}") {
                // Update or delete an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val formParameters = call.receiveParameters()
                when (formParameters.getOrFail("_action")) {
                    "update" -> {
                        val title = formParameters.getOrFail("title")
                        val body = formParameters.getOrFail("body")
                        dao.editArticle(id, title, body)
                        call.respondRedirect("/articles/$id")
                    }
                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }

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

