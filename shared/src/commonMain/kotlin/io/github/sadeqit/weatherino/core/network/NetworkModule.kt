package io.github.sadeqit.weatherino.core.network

import io.github.sadeqit.weatherino.data.local.CityPreferences
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApiImpl
import io.github.sadeqit.weatherino.data.repository.CityRepositoryImpl
import io.github.sadeqit.weatherino.domain.repository.CityRepository
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository
import io.github.sadeqit.weatherino.domain.repository.WeatherRepositoryImpl
import com.russhwolf.settings.Settings
import org.koin.dsl.module

val networkModule = module {

     single {
        createHttpClient()
    }

    single<OpenWeatherApi> {
        OpenWeatherApiImpl(
            httpClient = get()
        )
    }

    single<WeatherRepository> {
        WeatherRepositoryImpl(
            api = get(),
            cacheDataSource = get(),
            airQualityCacheDataSource = get(),
            forecastCacheDataSource = get()
        )
    }

    single<CityRepository> {
        CityRepositoryImpl(
            api = get()
        )
    }

    single<Settings> {
        Settings()
    }

    single {
        CityPreferences(
            settings = get()
        )
    }



}