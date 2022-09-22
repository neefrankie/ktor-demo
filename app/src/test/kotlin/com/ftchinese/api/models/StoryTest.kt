package com.ftchinese.api.models

import kotlinx.serialization.encodeToString
import java.time.ZonedDateTime
import kotlin.test.Test

class StoryTest {
    @Test
    fun serializeStory() {
        val timestamp = ZonedDateTime.now().toEpochSecond()

        val story = Story(
            id = "1234567890",
            kind = ContentKind.Story,
            createdAt = timestamp,
            updatedAt = timestamp,
            tier = Tier.Standard,
            title = "Article title",
            standfirst = "Standfirst",
            coverUrl = "http://example.org/abc",
            tags = listOf("tag1", "tag2"),
            audioUrl = null,
            bilingual = false,
            body = "article content"
        )

        val s = marshaller.encodeToString(story)

        println(s)
    }
}