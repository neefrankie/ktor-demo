package com.ftchinese.api.models

import org.jetbrains.exposed.sql.Table

data class RawStory(
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val accessRight: Int,
    val titleCn: String,
    val titleEn: String,
    val longLeadCn: String,
    val tag: String,
    val bylineDescCn: String,
    val bylineDescEn: String,
    val bylineAuthorCn: String,
    val bylineAuthorEn: String,
    val bylineStatusCn: String,
    val bylineStatusEn: String,
    val genre: String,
    val topic: String,
    val industry: String,
    val area: String,
    val bodyCn: String,
    val bodyEn: String,
) {
    val isBilingual: Boolean
        get() = bodyCn != "" && bodyEn != ""

    fun build(): Story {
        return Story(
            id = id,
            kind = ContentKind.Story,
            createdAt = createdAt,
            updatedAt = updatedAt,
            tier = when (accessRight) {
                1 -> Tier.Standard
                2 -> Tier.Premium
                else -> null
            },
            title = titleCn,
            standfirst = longLeadCn,
            tags = tag.split(","),
            bilingual = isBilingual,
            areas = area.split(","),
            genre = genre.split(","),
            industries = industry.split(","),
            topics = topic.split(","),
            contentCn = StoryDetails(
                title = titleCn,
                byline = Byline(
                    organization = bylineStatusCn,
                    authors = bylineAuthorCn,
                    locations = bylineDescCn
                ),
                body = bodyCn,
            ),
            contentEn = StoryDetails(
                title = titleEn,
                byline = Byline(
                    organization = bylineStatusEn,
                    authors = bylineAuthorEn,
                    locations = bylineDescEn,
                ),
                body = bodyEn,
            )
        )
    }
}
object Stories : Table(name = "story") {
    val id = varchar("id", 20)
    val createdAt = long("fileupdatetime")
    val updatedAt = long("last_publish_time")
    val accessRight = integer("access_right")
    val longLeadCn = varchar("clongleadbody", 2000)
    val tag = varchar("tag", 200)
    val titleCn = varchar("cheadline", 255)
    val titleEn = varchar("eheadline", 255)
    val bylineDescCn = varchar("cbyline_description", 255)
    val bylineDescEn = varchar("ebyline_description", 255)
    val bylineAuthorCn = varchar("cauthor", 200)
    val bylineAuthorEn = varchar("eauthor", 200)
    val bylineStatusCn = varchar("cbyline_status", 200)
    val bylineStatusEn = varchar("ebyline_status", 200)
    val area = varchar("area", 300)
    val genre = varchar("genre", 100)
    val industry = varchar("industry", 100)
    val topic = varchar("topic", 100)
    val bodyCn = text("cbody")
    val bodyEn = text("ebody")

    override val primaryKey = PrimaryKey(id)
}