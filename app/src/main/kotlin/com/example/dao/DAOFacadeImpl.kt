package com.example.dao

import com.example.dao.DatabaseFactory.articleQuery
import com.example.dao.DatabaseFactory.userQuery
import com.example.models.*
import com.google.common.hash.Hashing
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.*
import java.nio.charset.StandardCharsets
import java.time.ZonedDateTime
import java.util.UUID

class DAOFacadeImpl : DAOFacade {
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        title = row[Articles.title],
        body = row[Articles.body],
    )
    override suspend fun allArticles(): List<Article> = articleQuery {
        Articles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun article(id: Int): Article? = articleQuery {
        Articles
            .select { Articles.id eq id }
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun addNewArticle(title: String, body: String): Article? = articleQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun editArticle(id: Int, title: String, body: String): Boolean = articleQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    override suspend fun deleteArticle(id: Int): Boolean = articleQuery {
        Articles.deleteWhere { Articles.id eq id } > 0
    }

    private fun resultRowToAccount(row: ResultRow) = Account(
        id = row[UserTable.id],
        wxId = row[UserTable.wxId],
        email = row[UserTable.email],
        mobile = row[UserTable.mobile],
        userName = row[UserTable.userName],
        createdAt = row[UserTable.createdAt]
    )

    override suspend fun emailSignUp(email: String, password: String): Account? = userQuery {

        val insertStmt = UserTable.insert {
            it[UserTable.id] = UUID.randomUUID().toString()
            it[UserTable.email] = email
            it[UserTable.password] = encryptPassword(password)
            it[UserTable.createdAt] = ZonedDateTime.now()
                .toEpochSecond()
        }

        insertStmt.resultedValues?.singleOrNull()?.let(::resultRowToAccount)
    }

    override suspend fun emailLogin(email: String, password: String): Account? = userQuery {
        val pw = encryptPassword(password)
        UserTable
            .select {
                (UserTable.email eq email) and (UserTable.password eq pw)
            }
            .map(::resultRowToAccount)
            .singleOrNull()
    }

    private fun encryptPassword(str: String): ByteArray {
        return Hashing.sha256()
            .hashString(str, StandardCharsets.UTF_8)
            .asBytes()
    }
}

val dao: DAOFacade = DAOFacadeImpl().apply {
    runBlocking {
        if (allArticles().isEmpty()) {
            addNewArticle("The drive to develop!", "...it's what keeps me going.")
        }
    }
}