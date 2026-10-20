package io.github.sadeqit.weatherino.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.github.sadeqit.weatherino.database.WeatherinoDatabase
import java.io.File

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        val databaseDir = File(System.getProperty("user.home"), ".weatherino")
        if (!databaseDir.exists()) {
            databaseDir.mkdirs()
        }

        val databaseFile = File(databaseDir, "weatherino.db")
        val isNewDatabase = !databaseFile.exists()

        val driver = JdbcSqliteDriver("jdbc:sqlite:${databaseFile.absolutePath}")

        if (isNewDatabase) {
            WeatherinoDatabase.Schema.create(driver)
        }

        return driver
    }
}