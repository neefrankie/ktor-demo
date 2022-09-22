package com.ftchinese.api.dao

import com.ftchinese.api.models.Stories
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init() {
        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/cmstmp01",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "sampadm",
            password = "secret"
        )
        transaction(database) {
            SchemaUtils.create(Stories)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}