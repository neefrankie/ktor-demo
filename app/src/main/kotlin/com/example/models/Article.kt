package com.example.models

import com.example.validator.isValid
import com.example.validator.requiredRule
import org.jetbrains.exposed.sql.Table

class Article (
    val id: Int,
    var title: String,
    var body: String
)

object Articles : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 128)
    val body = varchar("body", 1024)

    override val primaryKey = PrimaryKey(id)
}

data class ArticleForm(
    val title: String,
    val body: String,
) {
    fun validate(): Map<String, String> {
        val error = mutableMapOf<String, String>()
        isValid(title, listOf(requiredRule("Title is required")))?.let {
            error["title"] = it
        }

        isValid(body, listOf(requiredRule("Body is required")))?.let {
            error["body"] = it
        }

        return error
    }
}