package io.github.sadeqit.weatherino.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit
import io.github.sadeqit.weatherino.domain.repository.SettingsRepository
import io.github.sadeqit.weatherino.presentation.screen.settings.SettingsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SettingsUiState(
            temperatureUnit = settingsRepository.getTemperatureUnit(),
            windSpeedUnit = settingsRepository.getWindSpeedUnit(),
            precipitationUnit = settingsRepository.getPrecipitationUnit(),
            pressureUnit = settingsRepository.getPressureUnit(),
            distanceUnit = settingsRepository.getDistanceUnit(),
        )
    )

    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun setTemperatureUnit(unit: TemperatureUnit) {
        settingsRepository.setTemperatureUnit(unit)

        _uiState.value = _uiState.value.copy(
            temperatureUnit = unit,
        )
    }

    fun setWindSpeedUnit(unit: WindSpeedUnit) {
        settingsRepository.setWindSpeedUnit(unit)

        _uiState.value = _uiState.value.copy(
            windSpeedUnit = unit,
        )
    }

    fun setPrecipitationUnit(unit: PrecipitationUnit) {
        settingsRepository.setPrecipitationUnit(unit)

        _uiState.value = _uiState.value.copy(
            precipitationUnit = unit,
        )
    }

    fun setPressureUnit(unit: PressureUnit) {
        settingsRepository.setPressureUnit(unit)

        _uiState.value = _uiState.value.copy(
            pressureUnit = unit,
        )
    }

    fun setDistanceUnit(unit: DistanceUnit) {
        settingsRepository.setDistanceUnit(unit)

        _uiState.value = _uiState.value.copy(
            distanceUnit = unit,
        )
    }

    fun resetUnits() {
        settingsRepository.resetUnits()

        _uiState.value = SettingsUiState()
    }
}