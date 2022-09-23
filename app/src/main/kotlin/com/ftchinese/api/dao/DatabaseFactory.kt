package com.ftchinese.api.dao

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private lateinit var contentDb: Database
    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/cmstmp01"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = "sampadm"
            password = "secret"
        }
        val dataSource = HikariDataSource(config)
        contentDb = Database.connect(dataSource)
        transaction(contentDb) {
            addLogger(StdOutSqlLogger)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO, contentDb) { block() }
}