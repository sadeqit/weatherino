package io.github.sadeqit.weatherino.core.database

import io.github.sadeqit.weatherino.data.local.AirQualityCacheDataSource
import io.github.sadeqit.weatherino.data.local.DatabaseFactory
import io.github.sadeqit.weatherino.data.local.DatabaseDriverFactory
import io.github.sadeqit.weatherino.data.local.ForecastCacheDataSource
import io.github.sadeqit.weatherino.data.local.WeatherCacheDataSource
import org.koin.dsl.module

val databaseModule = module {

    single {
        DatabaseFactory(
            driverFactory = get<DatabaseDriverFactory>()
        ).createDatabase()
    }

    single {
        WeatherCacheDataSource(
            database = get()
        )
    }
    single {
        AirQualityCacheDataSource(
            database = get()
        )
    }
    single {
        ForecastCacheDataSource(
            database = get()
        )
    }
}