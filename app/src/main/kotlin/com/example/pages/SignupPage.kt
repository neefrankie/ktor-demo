package com.example.pages

import com.example.models.Credentials
import kotlinx.html.*

fun HTML.signupPage(
    fieldErr: Map<String, String>? = null,
    value: Credentials? = null,
    formErr: String? = null
) {
    centerLayout(title = "Sign Up") {
        h4(classes = "text-center") {
            +"Sign Up"
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
            textInputControl(
                name = "confirmPassword",
                type = InputType.password,
                label = "Confirm Password",
                required = true,
                error = fieldErr?.get("confirmPassword")
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