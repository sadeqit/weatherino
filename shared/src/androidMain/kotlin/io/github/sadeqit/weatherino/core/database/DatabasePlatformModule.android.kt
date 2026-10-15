package io.github.sadeqit.weatherino.core.database

import android.content.Context
import io.github.sadeqit.weatherino.data.local.DatabaseDriverFactory
import org.koin.dsl.module

fun databasePlatformModule(
    context: Context
) = module {
    single {
        DatabaseDriverFactory(context)
    }
}