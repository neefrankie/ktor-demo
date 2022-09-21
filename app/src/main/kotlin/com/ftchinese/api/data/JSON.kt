package com.ftchinese.api.data

import kotlinx.serialization.json.Json

val marshaller = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}