package com.example.pages

import kotlinx.html.*

fun HTML.newArticlePage() {
    articleLayout("Create article") {
        div {
            h3 { +"Crate article" }
            form(
                action = "/articles",
                method = FormMethod.post
            ) {
                textInputControl(
                    name = "title",
                    type = InputType.text,
                    label = "Title",
                )

                textAreaControl(
                    name = "body",
                    label = "Body",
                    content = ""
                )

                submitButton()
            }
        }
    }
}