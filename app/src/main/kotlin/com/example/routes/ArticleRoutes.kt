package com.example.routes

import com.example.dao.dao
import com.example.models.Account
import com.example.models.Article
import com.example.models.ArticleForm
import com.example.pages.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.server.util.*

fun Route.articleRouting() {
    route("/articles") {
        get {
            // Show a list of articles
            val articles = dao.allArticles()
            call.respondHtml {
                val account = call.sessions.get<Account>()
                articleListPage(articles, account)
            }
        }
        get("{id}") {
            // Show an article with a specific id
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val article = dao.article(id)
            call.respondHtml {
                if (article != null) {
                    val account = call.sessions.get<Account>()
                    articlePage(article, account)
                } else {
                    notFoundPage()
                }
            }
        }
        authenticate("auth-session") {
            get("new") {
                val account = call.sessions.get<Account>()
                // Show a page with fields for creating a new article
                call.respondHtml(status = HttpStatusCode.OK) {
                    newArticlePage(account = account)
                }
            }
            post {
                // save an article
                val formParameters = call.receiveParameters()
                val v = ArticleForm(
                    title = formParameters.getOrFail("title"),
                    body = formParameters.getOrFail("body")
                )
                val err = v.validate()
                if (err.isEmpty()) {
                    val article = dao.addNewArticle(v.title, v.body)
                    call.respondRedirect("/articles/${article?.id}")
                } else {
                    val account = call.sessions.get<Account>()
                    call.respondHtml(status = HttpStatusCode.OK) {
                        newArticlePage(
                            fieldErr = err,
                            value = v,
                            account = account
                        )
                    }
                }
            }

            get("{id}/edit") {
                // Show a page with fields for editing an article
                val id = call.parameters.getOrFail<Int>("id").toInt()
                val article = dao.article(id)
                val account = call.sessions.get<Account>()
                call.respondHtml {
                    if (article != null) {
                        articleEditPage(article, account = account)
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
                        val v = ArticleForm(
                            title = formParameters.getOrFail("title"),
                            body = formParameters.getOrFail("body")
                        )
                        val err = v.validate()
                        if (err.isEmpty()) {
                            dao.editArticle(id, v.title, v.body)
                            call.respondRedirect("/articles/$id")
                        } else {
                            val account = call.sessions.get<Account>()
                            call.respondHtml {
                                articleEditPage(Article(
                                    id = id,
                                    title = v.title,
                                    body = v.body
                                ), account = account)
                            }
                        }
                    }
                    "delete" -> {
                        dao.deleteArticle(id)
                        call.respondRedirect("/articles")
                    }
                }
            }
        }
    }
}