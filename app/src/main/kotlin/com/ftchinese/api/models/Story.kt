package com.ftchinese.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    val id: String,
    val kind: ContentKind,
    val createdAt: Long,
    val updatedAt: Long,
    val tier: Tier,
    val title: String,
    val standfirst: String,
    val coverUrl: String?,
    val tags: List<String>,
    val audioUrl: String? = null,
    val bilingual: Boolean = false,
    val body: String,
)

