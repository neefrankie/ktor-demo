package com.ftchinese.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Tier(val symbol: String) {
    @SerialName("standard")
    Standard("standard"),
    @SerialName("premium")
    Premium("premium")
}