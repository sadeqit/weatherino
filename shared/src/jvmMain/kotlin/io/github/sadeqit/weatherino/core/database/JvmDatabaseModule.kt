package io.github.sadeqit.weatherino.core.database

import io.github.sadeqit.weatherino.data.local.DatabaseDriverFactory
import org.koin.dsl.module

val jvmDatabaseModule = module {
    single<DatabaseDriverFactory> {
        DatabaseDriverFactory()
    }
}