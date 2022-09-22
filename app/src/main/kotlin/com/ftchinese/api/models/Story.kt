package com.ftchinese.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Story(
    // Teaser
    val id: String,
    val kind: ContentKind,
    val createdAt: Long,
    val updatedAt: Long,
    val tier: Tier? = null,
    val title: String,
    val standfirst: String,
    val coverUrl: String? = null,
    val tags: List<String>,
    val audioUrl: String? = null,

    // Details
    val bilingual: Boolean = false,
    val areas: List<String>,
    val genre: List<String>,
    val industries: List<String>,
    val topics: List<String>,

    val contentCn: StoryDetails,
    val contentEn: StoryDetails,
)

@Serializable
data class StoryDetails(
    val title: String,
    val byline: Byline,
    val body: String,
)

@Serializable
data class Byline(
    val organization: String = "",
    val authors: String,
    val locations: String,
)
