package io.github.sadeqit.weatherino.presentation.viewmodel

import io.github.sadeqit.weatherino.domain.model.City

data class CitySelectionUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val cities: List<City> = emptyList(),
    val error: String? = null
)