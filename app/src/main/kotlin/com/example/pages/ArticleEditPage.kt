package com.example.pages

import com.example.models.Article
import kotlinx.html.*

fun HTML.articleEditPage(article: Article) {
    articleLayout(title = article.title) {
        div(classes = "mt-3") {

            div(classes = "d-flex justify-content-between align-items-center") {
                h3 {
                    +"Edit article"
                }

                form(
                    action = "/articles/${article.id}",
                    method = FormMethod.post
                ) {
                    input(
                        type = InputType.hidden,
                        name = "_action",
                    ) {
                        this.value = "delete"
                    }

                    button(
                        type = ButtonType.submit,
                        classes = "btn btn-danger btn-sm"
                    ) {
                        +"Delete"
                    }
                }
            }

            form(
                action = "/articles/${article.id}",
                method = FormMethod.post
            ) {

                input(
                    type = InputType.hidden,
                    name = "_action",
                ) {
                    this.value = "update"
                }

                textInputControl(
                    name = "title",
                    type = InputType.text,
                    label = "Title",
                    value = article.title
                )

                textAreaControl(
                    name = "body",
                    label = "Body",
                    content = article.body
                )

                submitButton()
            }
        }
    }
}