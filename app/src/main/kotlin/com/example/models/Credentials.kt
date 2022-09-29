package com.example.models

import com.example.validator.isValid
import com.example.validator.passwordRules
import com.example.validator.ruleEmailValid

data class Credentials(
    val email: String,
    val password: String,
    val confirmPassword: String = "",
) {
    private val matched: Boolean
        get() = password == confirmPassword

    fun validate(isSignUp: Boolean = false): Map<String, String> {
        val error = mutableMapOf<String, String>()

        isValid(email, listOf(ruleEmailValid))?.let {
            error["email"] = it
        }

        isValid(password, passwordRules())?.let {
            error["password"] = it
        }

        if (isSignUp) {
            val err = isValid(confirmPassword, passwordRules(true))
            if (!err.isNullOrBlank()) {
                error["confirmPassword"] = err
            } else if (!matched) {
                error["confirmPassword"] = "Confirmation password does not match"
            }
        }

        return error
    }
}
