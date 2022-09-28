package com.example.pages

import kotlinx.html.*

fun HTML.signupPage() {
    centerLayout(title = "Sign Up") {
        h4(classes = "text-center") {
            +"Sign Up"
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
            textInputControl(
                name = "confirmPassword",
                type = InputType.password,
                label = "Confirm Password"
            )

            submitButton()
        }

        div(classes = "hstack gap-3 mt-3") {
            a(href = "/auth/login", classes = "ms-auto") {
                +"Log In"
            }
        }
    }
}