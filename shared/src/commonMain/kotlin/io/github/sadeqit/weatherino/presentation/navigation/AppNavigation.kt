package io.github.sadeqit.weatherino.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sadeqit.weatherino.platform.location.LocationProvider
import io.github.sadeqit.weatherino.presentation.component.drawer.WeatherDrawerContent
import io.github.sadeqit.weatherino.presentation.screen.about.AboutScreen
import io.github.sadeqit.weatherino.presentation.screen.air.AirQualityScreen
import io.github.sadeqit.weatherino.presentation.screen.city.CitySelectionScreen
import io.github.sadeqit.weatherino.presentation.screen.contact.ContactScreen
import io.github.sadeqit.weatherino.presentation.screen.home.HomeScreen
import io.github.sadeqit.weatherino.presentation.screen.settings.SettingsScreen
import io.github.sadeqit.weatherino.presentation.screen.splash.SplashScreen
import io.github.sadeqit.weatherino.presentation.screen.support.SupportScreen
import io.github.sadeqit.weatherino.presentation.viewmodel.SettingsViewModel
import io.github.sadeqit.weatherino.presentation.viewmodel.SplashViewModel
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavigation(
    onExit: () -> Unit
) {
    val navController = rememberNavController()
    val weatherViewModel: WeatherViewModel = koinViewModel()
    val uiState by weatherViewModel.uiState.collectAsStateWithLifecycle()
    val locationProvider: LocationProvider = koinInject()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )
    val settingsViewModel: SettingsViewModel = koinViewModel()
    val settingsUiState by settingsViewModel.uiState.collectAsStateWithLifecycle()



    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            WeatherDrawerContent(
                currentItem = null,
                onItemClick = { item ->
                    scope.launch {
                        drawerState.close()

                        handleDrawerItem(
                            item = item,
                            navController = navController,
                            onExitClick = onExit
                        )
                    }
                }
            )
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = SplashRoute,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popEnterTransition = { EnterTransition.None },
            popExitTransition = { ExitTransition.None }
        ) {
            composable<SplashRoute> {
                val splashViewModel: SplashViewModel = koinViewModel()
                val splashUiState by splashViewModel.uiState.collectAsStateWithLifecycle()

                SplashScreen(
                    onConnectionSuccess = {
                        val savedCity = splashUiState.savedCity

                        if (savedCity != null) {
                            weatherViewModel.loadWeather(
                                latitude = savedCity.latitude,
                                longitude = savedCity.longitude,
                                locationName = savedCity.name
                            )

                            navController.navigate(HomeRoute) {
                                popUpTo(SplashRoute) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(CitySelectionRoute) {
                                popUpTo(SplashRoute) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }

            composable<CitySelectionRoute> {
                CitySelectionScreen(
                    onCitySelected = { city ->
                        weatherViewModel.loadWeather(
                            latitude = city.latitude,
                            longitude = city.longitude,
                            locationName = city.name
                        )

                        navController.navigate(HomeRoute) {
                            popUpTo(CitySelectionRoute) {
                                inclusive = true
                            }
                        }
                    },
                    onLocationClick = {
                        scope.launch {
                            val location = locationProvider.getCurrentLocation()

                            if (location != null) {
                                weatherViewModel.loadWeather(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )

                                navController.navigate(HomeRoute) {
                                    popUpTo(CitySelectionRoute) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                )
            }

            composable<HomeRoute> {

                HomeScreen(
                    uiState = uiState,
                    settingsUiState = settingsUiState,
                    onAirQualityClick = {
                        navController.navigate(AirQualityRoute)
                    },
                    onSearchQueryChange = weatherViewModel::searchCities,
                    onCitySelected = { city ->
                        weatherViewModel.loadWeather(
                            latitude = city.latitude,
                            longitude = city.longitude,
                            locationName = city.name
                        )
                    },
                    onRetry = weatherViewModel::retryWeather,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isClosed && !drawerState.isAnimationRunning) {
                                drawerState.open()
                            }
                        }
                    },
                    onLocationClick = {
                        scope.launch {
                            val location = locationProvider.getCurrentLocation()

                            if (location != null) {
                                weatherViewModel.loadWeather(
                                    latitude = location.latitude,
                                    longitude = location.longitude
                                )
                            }
                        }
                    }
                )
            }

            composable<AirQualityRoute> {
                uiState.airQuality?.let { airQuality ->
                    AirQualityScreen(
                        airQuality = airQuality,
                        onBackClick = {
                             navController.navigateUp()
                        }
                    )
                }
            }

            composable<ContactRoute> {
                ContactScreen(
                    onBackClick = {
                         navController.navigateUp()
                    },
                )
            }
            composable<AboutRoute> {
                AboutScreen(
                    onBackClick = {
                         navController.navigateUp()
                    },
                )
            }
            composable<SupportRoute> {
                SupportScreen(
                    onBackClick = {
                         navController.navigateUp()
                    },
                )
            }
            composable<SettingsRoute> {
                SettingsScreen(
                    uiState = settingsUiState,
                    onTemperatureUnitChange = settingsViewModel::setTemperatureUnit,
                    onWindSpeedUnitChange = settingsViewModel::setWindSpeedUnit,
                    onPrecipitationUnitChange = settingsViewModel::setPrecipitationUnit,
                    onPressureUnitChange = settingsViewModel::setPressureUnit,
                    onDistanceUnitChange = settingsViewModel::setDistanceUnit,
                    onResetUnits = settingsViewModel::resetUnits,
                    onBackClick = {
                         navController.navigateUp()
                    },
                )
            }
        }
    }
}