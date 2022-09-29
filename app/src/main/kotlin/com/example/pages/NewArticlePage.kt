package com.example.pages

import com.example.models.Account
import com.example.models.ArticleForm
import kotlinx.html.*

fun FORM.articleForm(
    value: ArticleForm? = null,
    fieldErr: Map<String, String>? = null,
) {
    textInputControl(
        name = "title",
        type = InputType.text,
        label = "Title",
        value = value?.title ?: "",
        required = true,
        error = fieldErr?.get("title")
    )

    textAreaControl(
        name = "body",
        label = "Body",
        content = value?.body ?: "",
        required = true,
        error = fieldErr?.get("body")
    )

    submitButton()
}

fun HTML.newArticlePage(
    value: ArticleForm? = null,
    fieldErr: Map<String, String>? = null,
    formErr: String? = null,
    account: Account?
) {
    articleLayout("Create article", account) {
        div {
            h3 { +"Crate article" }

            if (!formErr.isNullOrEmpty()) {
                alert(formErr)
            }
            form(
                action = "/articles",
                method = FormMethod.post
            ) {
                articleForm(value, fieldErr)
            }
        }
    }
}