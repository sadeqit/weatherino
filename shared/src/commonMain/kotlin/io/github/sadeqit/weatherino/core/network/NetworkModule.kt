package io.github.sadeqit.weatherino.core.network

import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApiImpl
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository
import io.github.sadeqit.weatherino.domain.repository.WeatherRepositoryImpl
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
            api = get()
        )
    }
}