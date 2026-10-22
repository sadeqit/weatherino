package io.github.sadeqit.weatherino.domain.repository

import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit

interface SettingsRepository {

    fun getTemperatureUnit(): TemperatureUnit

    fun setTemperatureUnit(unit: TemperatureUnit)

    fun getWindSpeedUnit(): WindSpeedUnit

    fun setWindSpeedUnit(unit: WindSpeedUnit)

    fun getPrecipitationUnit(): PrecipitationUnit

    fun setPrecipitationUnit(unit: PrecipitationUnit)

    fun getPressureUnit(): PressureUnit

    fun setPressureUnit(unit: PressureUnit)

    fun getDistanceUnit(): DistanceUnit

    fun setDistanceUnit(unit: DistanceUnit)

    fun resetUnits()
}