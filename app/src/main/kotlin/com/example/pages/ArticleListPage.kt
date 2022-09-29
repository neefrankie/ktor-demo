package com.example.pages

import com.example.models.Account
import com.example.models.Article
import kotlinx.html.*

fun HTML.articleListPage(articles: List<Article>, account: Account?) {
    articleLayout(title = "All Journals", account = account) {
        div(classes = "text-end border-bottom mt-3 pb-3") {
            a(
                classes = "btn btn-link",
                href = "/articles/new"
            ) {
                +"Create article"
            }
        }
        articles.forEach { article ->
            div(classes = "border-bottom mb-3 mt-3") {
                h3 {
                    a(href = "/articles/${article.id}") {
                        +article.title
                    }
                }
                p {
                    +article.body
                }
            }
        }
    }
}