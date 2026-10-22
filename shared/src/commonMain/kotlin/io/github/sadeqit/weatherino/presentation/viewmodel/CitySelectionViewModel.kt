package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CitySelectionViewModel(
    private val searchCities: SearchCitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CitySelectionUiState())
    val uiState: StateFlow<CitySelectionUiState> = _uiState.asStateFlow()

    fun search(query: String) {
        _uiState.value = _uiState.value.copy(
            query = query
        )

        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                cities = emptyList(),
                error = null
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            runCatching {
                searchCities(query)
            }.onSuccess { cities ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    cities = cities
                )
            }.onFailure { exception ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    cities = emptyList(),
                    error = exception.message ?: "خطا در جستجوی شهر"
                )
            }
        }
    }
}