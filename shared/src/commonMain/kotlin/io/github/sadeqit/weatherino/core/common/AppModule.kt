package io.github.sadeqit.weatherino.core.common

import io.github.sadeqit.weatherino.core.network.networkModule
import io.github.sadeqit.weatherino.domain.usecase.CheckNetworkConnectionUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetAirQualityUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetCurrentWeatherUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetForecastUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetSavedCityUseCase
import io.github.sadeqit.weatherino.domain.usecase.SearchCitiesUseCase
import io.github.sadeqit.weatherino.presentation.viewmodel.CitySelectionViewModel
import io.github.sadeqit.weatherino.presentation.viewmodel.SplashViewModel
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

    factory {
        SearchCitiesUseCase(
            cityRepository = get()
        )
    }

    factory {
        CheckNetworkConnectionUseCase(
            networkChecker = get()
        )
    }
    factory {
        GetSavedCityUseCase(
            cityPreferences = get()
        )
    }

    viewModel {
        SplashViewModel(
            checkNetworkConnection = get(),
            getSavedCity = get()
        )
    }

    viewModel {
        CitySelectionViewModel(
            searchCities = get()
        )
    }

    viewModel {
        WeatherViewModel(
            getCurrentWeather = get(),
            getAirQuality = get(),
            getForecast = get(),
            searchCitiesUseCase = get(),
            cityPreferences = get()
        )
    }
}

val appModules: List<Module> = listOf(
    networkModule,
    domainModule
)