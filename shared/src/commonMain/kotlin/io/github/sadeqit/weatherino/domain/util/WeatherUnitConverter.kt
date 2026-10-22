package io.github.sadeqit.weatherino.domain.util

import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit

object WeatherUnitConverter {

    fun temperature(
        celsius: Double,
        unit: TemperatureUnit,
    ): Double {
        return when (unit) {
            TemperatureUnit.Celsius -> celsius
            TemperatureUnit.Fahrenheit -> (celsius * 9.0 / 5.0) + 32.0
        }
    }

    fun windSpeed(
        metersPerSecond: Double,
        unit: WindSpeedUnit,
    ): Double {
        return when (unit) {
            WindSpeedUnit.MetersPerSecond -> metersPerSecond
            WindSpeedUnit.KilometersPerHour -> metersPerSecond * 3.6
            WindSpeedUnit.MilesPerHour -> metersPerSecond * 2.236936
        }
    }

    fun precipitation(
        millimeters: Double,
        unit: PrecipitationUnit,
    ): Double {
        return when (unit) {
            PrecipitationUnit.Millimeter -> millimeters
            PrecipitationUnit.Inch -> millimeters / 25.4
        }
    }

    fun pressure(
        hectopascal: Int,
        unit: PressureUnit,
    ): Double {
        return when (unit) {
            PressureUnit.Hectopascal -> hectopascal.toDouble()
            PressureUnit.MillimeterOfMercury -> hectopascal * 0.750062
            PressureUnit.InchOfMercury -> hectopascal * 0.02953
        }
    }

    fun distance(
        meters: Int,
        unit: DistanceUnit,
    ): Double {
        return when (unit) {
            DistanceUnit.Kilometer -> meters / 1000.0
            DistanceUnit.Mile -> meters / 1609.344
        }
    }
}