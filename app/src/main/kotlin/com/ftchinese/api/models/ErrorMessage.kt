package com.ftchinese.api.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    val message: String,
    val field: String? = null,
    val code: String? = null,
)

fun errNotFound(): ErrorMessage {
    return ErrorMessage(
        message = "Not found"
    )
}
