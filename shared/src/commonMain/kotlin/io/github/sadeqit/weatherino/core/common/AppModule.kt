package io.github.sadeqit.weatherino.core.common

import io.github.sadeqit.weatherino.core.network.networkModule
import io.github.sadeqit.weatherino.domain.usecase.GetAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCurrentWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetForecastUseCase
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val domainModule = module {

    factory {
        GetCurrentWeatherUseCase(
            repository = get()
        )
    }

    factory {
        GetAirQualityUseCase(
            repository = get()
        )
    }

    factory {
        GetForecastUseCase(
            repository = get()
        )
    }

    viewModel {
        WeatherViewModel(
            getCurrentWeather = get(),
            getAirQuality = get(),
            getForecast = get()
        )
    }
}

val appModules: List<Module> = listOf(
    networkModule,
    domainModule
)