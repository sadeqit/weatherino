package io.github.sadeqit.weatherino.data.local

import app.cash.sqldelight.db.SqlDriver
import io.github.sadeqit.weatherino.database.WeatherinoDatabase

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

class DatabaseFactory(
    private val driverFactory: DatabaseDriverFactory
) {
    fun createDatabase(): WeatherinoDatabase {
        return WeatherinoDatabase(
            driver = driverFactory.createDriver()
        )
    }
}