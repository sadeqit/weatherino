package io.github.sadeqit.weatherino.presentation.screen.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.domain.model.City
import io.github.sadeqit.weatherino.presentation.component.air.AirQualityCard
import io.github.sadeqit.weatherino.presentation.component.background.WeatherBackground
import io.github.sadeqit.weatherino.presentation.component.home.WeatherHeader
import io.github.sadeqit.weatherino.presentation.component.weather.CollapsingWeatherCard
import io.github.sadeqit.weatherino.presentation.component.weather.DailyForecast
import io.github.sadeqit.weatherino.presentation.component.weather.HourlyForecast
import io.github.sadeqit.weatherino.presentation.component.weather.WeatherDetailsRow
import io.github.sadeqit.weatherino.presentation.component.weather.WeatherExtraDetails
import io.github.sadeqit.weatherino.presentation.screen.city.CityItem
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherUiState
import io.github.sadeqit.weatherino.presentation.viewmodel.WeatherViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    uiState: WeatherUiState,
    onAirQualityClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onCitySelected: (City) -> Unit
){
    var isSearchActive by rememberSaveable {
        mutableStateOf(false)
    }

    val scrollState = rememberSaveable(
        saver = ScrollState.Saver
    ) {
        ScrollState(0)
    }

    var collapseProgress by rememberSaveable {
        mutableFloatStateOf(0f)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll(
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (available.y < 0 && collapseProgress < 1f) {
                    collapseProgress =
                        (collapseProgress - available.y / 180f)
                            .coerceIn(0f, 1f)

                    return available
                }

                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                if (available.y > 0 && scrollState.value == 0 && collapseProgress > 0f) {
                    collapseProgress =
                        (collapseProgress - available.y / 180f)
                            .coerceIn(0f, 1f)

                    return available
                }

                return Offset.Zero
            }
        }
    }

    WeatherBackground {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            WeatherHeader(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 8.dp),
                locationName = uiState.weather?.locationName ?: "",
                isSearchActive = isSearchActive,
                searchQuery = uiState.searchQuery,
                onMenuClick = {
                    // Drawer
                },
                onSearchClick = {
                    isSearchActive = true
                },
                onSearchQueryChange = onSearchQueryChange,
                onSearchClose = {
                    isSearchActive = false
                }
            )

            if (isSearchActive && uiState.searchQuery.isNotBlank()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    uiState.searchCities.forEach { city ->
                        CityItem(
                            city = city,
                            onClick = {
                                onCitySelected(city)
                                isSearchActive = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))


            uiState.weather?.let { weather ->
                CollapsingWeatherCard(
                    weather = weather,
                    collapseProgress = collapseProgress,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                uiState.weather?.let { weather ->

                    WeatherDetailsRow(
                        humidity = weather.humidity,
                        windSpeed = weather.windSpeed,
                        visibility = weather.visibility
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    HourlyForecast(
                        forecasts = uiState.forecast
                    )

                    uiState.airQuality?.let { airQuality ->
                        Spacer(modifier = Modifier.height(16.dp))

                        AirQualityCard(
                            airQuality = airQuality,
                            onClick = onAirQualityClick
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    DailyForecast(
                        forecasts = uiState.forecast
                    )

                    uiState.weather.let { weather ->
                        Spacer(modifier = Modifier.height(16.dp))

                        uiState.forecast.firstOrNull()?.let { forecast ->
                            WeatherExtraDetails(
                                weather = weather,
                                date = forecast.dateTimeText
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(70.dp))
                }
            }
        }
    }
}