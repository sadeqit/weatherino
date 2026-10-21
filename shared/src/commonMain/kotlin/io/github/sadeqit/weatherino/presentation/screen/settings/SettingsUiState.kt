package io.github.sadeqit.weatherino.presentation.screen.settings

import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit

data class SettingsUiState(
    val temperatureUnit: TemperatureUnit = TemperatureUnit.Celsius,
    val windSpeedUnit: WindSpeedUnit = WindSpeedUnit.KilometersPerHour,
    val precipitationUnit: PrecipitationUnit = PrecipitationUnit.Millimeter,
    val pressureUnit: PressureUnit = PressureUnit.Hectopascal,
    val distanceUnit: DistanceUnit = DistanceUnit.Kilometer,
)