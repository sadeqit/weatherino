package io.github.sadeqit.weatherino.presentation.viewmodel

import io.github.sadeqit.weatherino.domain.usecase.SavedCity

data class SplashUiState(
    val isCheckingConnection: Boolean = false,
    val isConnected: Boolean = false,
    val hasConnectionError: Boolean = false,
    val savedCity: SavedCity? = null
)