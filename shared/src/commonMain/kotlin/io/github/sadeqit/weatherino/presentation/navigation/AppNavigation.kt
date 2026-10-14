package io.github.sadeqit.weatherino.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sadeqit.weatherino.presentation.screen.air.AirQualityScreen
import io.github.sadeqit.weatherino.presentation.screen.home.HomeScreen
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherUiState

@Composable
fun AppNavigation(
    uiState: WeatherUiState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(
                uiState = uiState,
                onAirQualityClick = {
                    navController.navigate(AirQualityRoute)
                }
            )
        }

        composable<AirQualityRoute> {
            uiState.airQuality?.let { airQuality ->
                AirQualityScreen(
                    airQuality = airQuality,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}