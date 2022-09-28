package com.example.models

import org.jetbrains.exposed.sql.Table

data class Account(
    val id: String,
    val wxId: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    val userName: String? = null,
    val createdAt: Long,
)

data class EmailSignUp(
    val email: String,
    val password: String,
)

object UserTable : Table() {
    val id = char("id", 36)
    val mobile = varchar("mobile", 16)
        .nullable()
        .uniqueIndex()
    val wxId = varchar("wechat_id", 256)
        .nullable()
        .uniqueIndex()
    val email = varchar("email", 64)
        .nullable()
        .uniqueIndex()
    val password = binary("password_sha256", 256)
        .nullable()
    val userName = varchar("user_name", 64)
        .nullable()
        .index()
    val createdAt = long("created_at")

    override val primaryKey = PrimaryKey(id)
}