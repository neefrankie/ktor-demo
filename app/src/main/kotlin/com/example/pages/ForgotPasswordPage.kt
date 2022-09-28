package com.example.pages

import kotlinx.html.*

fun HTML.forgotPasswordPage() {
    centerLayout("Forgot password") {
        h4(classes = "text-center") {
            +"Forgot password"
        }

        form(method = FormMethod.post) {
            textInputControl(
                name = "email",
                type = InputType.email,
                label = "Email",
                desc = "We will send you a letter to help you reset password"
            )

            submitButton(
                text = "Send password reset letter"
            )
        }
    }
}