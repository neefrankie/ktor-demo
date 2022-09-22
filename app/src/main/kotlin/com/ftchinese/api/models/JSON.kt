package com.ftchinese.api.models

import kotlinx.serialization.json.Json

val marshaller = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
}