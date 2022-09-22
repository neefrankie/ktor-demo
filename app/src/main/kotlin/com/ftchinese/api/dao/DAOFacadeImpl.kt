package com.ftchinese.api.dao

import com.ftchinese.api.dao.DatabaseFactory.dbQuery
import com.ftchinese.api.models.RawStory
import com.ftchinese.api.models.Stories
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToStory(row: ResultRow) = RawStory(
        id = row[Stories.id],
        createdAt = row[Stories.createdAt],
        updatedAt = row[Stories.updatedAt],
        accessRight = row[Stories.accessRight],
        titleCn = row[Stories.titleCn],
        titleEn = row[Stories.titleEn],
        longLeadCn = row[Stories.longLeadCn],
        tag = row[Stories.tag],
        bylineDescCn = row[Stories.bylineDescCn],
        bylineDescEn = row[Stories.bylineDescEn],
        bylineAuthorCn = row[Stories.bylineAuthorCn],
        bylineAuthorEn = row[Stories.bylineAuthorEn],
        bylineStatusCn = row[Stories.bylineStatusCn],
        bylineStatusEn = row[Stories.bylineStatusEn],
        genre = row[Stories.genre],
        topic = row[Stories.topic],
        industry = row[Stories.industry],
        area = row[Stories.area],
        bodyCn = row[Stories.bodyCn],
        bodyEn = row[Stories.bodyEn],
    )

    override suspend fun story(id: String): RawStory? = dbQuery {
        Stories
            .select { Stories.id eq id }
            .map(::resultRowToStory)
            .singleOrNull()
    }
}

val dao: DAOFacade = DAOFacadeImpl()