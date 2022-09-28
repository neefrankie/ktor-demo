package com.example.pages

import com.example.models.Article
import kotlinx.html.*

fun HTML.articlePage(article: Article) {
    articleLayout(title = article.title) {
        div(classes = "text-end mt-3 mb-3 pb-3 border-bottom") {
            a(href = "/articles/${article.id}/edit") {
                +"Edit"
            }
        }
        div {
            h3 {
                +article.title
            }
            p {
                +article.body
            }
        }
    }
}