package com.example.pages

import com.example.models.Account
import com.example.models.Article
import com.example.models.ArticleForm
import kotlinx.html.*

fun HTML.articleEditPage(
    article: Article,
    fieldErr: Map<String, String>? = null,
    formErr: String? = null,
    account: Account?
) {
    articleLayout(title = article.title, account) {
        div(classes = "mt-3") {

            div(classes = "d-flex justify-content-between align-items-center") {
                h3 {
                    +"Edit article"
                }

                if (!formErr.isNullOrEmpty()) {
                    alert(formErr)
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

                articleForm(
                    value = ArticleForm(article.title, article.body),
                    fieldErr = fieldErr
                )
            }
        }
    }
}