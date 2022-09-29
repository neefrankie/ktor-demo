package com.example.pages

import kotlinx.html.*

fun HTML.loginPage() {
    centerLayout(title = "Login") {
        h4(classes = "text-center") {
            +"Log In"
        }

        form(method = FormMethod.post) {
            textInputControl(
                name = "email",
                type = InputType.email,
                label = "Email"
            )
            textInputControl(
                name = "password",
                type = InputType.password,
                label = "Password"
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