package com.example.pages

import com.example.models.Credentials
import kotlinx.html.*

fun HTML.loginPage(
    fieldErr: Map<String, String>? = null,
    value: Credentials? = null,
    formErr: String? = null
) {
    centerLayout(title = "Login") {
        h4(classes = "text-center") {
            +"Log In"
        }

        if (!formErr.isNullOrBlank()) {
            alert(formErr)
        }

        form(method = FormMethod.post) {
            textInputControl(
                name = "email",
                type = InputType.email,
                label = "Email",
                required = true,
                value = value?.email ?: "",
                error = fieldErr?.get("email")
            )
            textInputControl(
                name = "password",
                type = InputType.password,
                label = "Password",
                required = true,
                error = fieldErr?.get("password")
            )

            submitButton()
        }

        div(classes = "hstack gap-3 mt-3") {
            a(href = "/auth/forgot-password") {
                +"Forgot password?"
            }
            a(href = "/auth/signup", classes = "ms-auto") {
                +"Sign up"
            }
        }
    }
}