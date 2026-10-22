package io.github.sadeqit.weatherino.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import io.github.sadeqit.weatherino.database.WeatherinoDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = WeatherinoDatabase.Schema,
            context = context,
            name = "weatherino.db"
        )
    }
}