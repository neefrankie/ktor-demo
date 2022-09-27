package com.example.dao

import com.example.models.Articles
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.github.cdimascio.dotenv.dotenv
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    private lateinit var myDB: Database
    private lateinit var h2DB: Database
    fun init() {
        val dotEnv = dotenv()
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:3306/cmstmp01"
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = dotEnv["MYSQL_USER"]
            password = dotEnv["MYSQL_PASS"]
        }
        val dataSource = HikariDataSource(config)
        myDB = Database.connect(dataSource)
        h2DB = Database.connect(
            url = "jdbc:h2:file:./build/db",
            driver = "org.h2.Driver"
        )
        transaction(myDB) {
            addLogger(StdOutSqlLogger)
        }
        transaction(h2DB) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Articles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO, myDB) { block() }

    suspend fun <T> articleQuery(block: suspend () -> T) = newSuspendedTransaction(Dispatchers.IO) { block() }
}