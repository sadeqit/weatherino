package io.github.sadeqit.weatherino.core.common

import io.github.sadeqit.weatherino.core.network.NetworkChecker
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.data.repository.WebWeatherRepository
import io.github.sadeqit.weatherino.domain.repository.WeatherRepository
import io.github.sadeqit.weatherino.platform.location.LocationProvider
import io.github.sadeqit.weatherino.platform.location.WebLocationProvider
import org.koin.dsl.module

actual val platformModules = listOf(
    module {
        single {
            NetworkChecker()
        }

        single<WeatherRepository> {
            WebWeatherRepository(
                api = get<OpenWeatherApi>()
            )
        }
        single<LocationProvider> {
            WebLocationProvider()
        }
    }
)