package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sadeqit.weatherino.domain.usecase.CheckNetworkConnectionUseCase
import io.github.sadeqit.weatherino.domain.usecase.GetSavedCityUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val checkNetworkConnection: CheckNetworkConnectionUseCase,
    private val getSavedCity: GetSavedCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    fun checkConnection() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isCheckingConnection = true,
                hasConnectionError = false
            )

            val isConnected = checkNetworkConnection()

            _uiState.value = _uiState.value.copy(
                isCheckingConnection = false,
                isConnected = isConnected,
                hasConnectionError = !isConnected,
                savedCity = if (isConnected) getSavedCity() else null
            )
        }
    }
}