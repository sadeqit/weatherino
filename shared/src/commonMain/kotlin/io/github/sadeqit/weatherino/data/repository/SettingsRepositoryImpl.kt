package io.github.sadeqit.weatherino.data.repository

import com.russhwolf.settings.Settings
import io.github.sadeqit.weatherino.domain.model.DistanceUnit
import io.github.sadeqit.weatherino.domain.model.PrecipitationUnit
import io.github.sadeqit.weatherino.domain.model.PressureUnit
import io.github.sadeqit.weatherino.domain.model.TemperatureUnit
import io.github.sadeqit.weatherino.domain.model.WindSpeedUnit
import io.github.sadeqit.weatherino.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settings: Settings,
) : SettingsRepository {

    override fun getTemperatureUnit(): TemperatureUnit {
        return settings.getEnum(
            key = KEY_TEMPERATURE_UNIT,
            default = TemperatureUnit.Celsius,
        )
    }

    override fun setTemperatureUnit(unit: TemperatureUnit) {
        settings.putString(
            key = KEY_TEMPERATURE_UNIT,
            value = unit.name,
        )
    }

    override fun getWindSpeedUnit(): WindSpeedUnit {
        return settings.getEnum(
            key = KEY_WIND_SPEED_UNIT,
            default = WindSpeedUnit.KilometersPerHour,
        )
    }

    override fun setWindSpeedUnit(unit: WindSpeedUnit) {
        settings.putString(
            key = KEY_WIND_SPEED_UNIT,
            value = unit.name,
        )
    }

    override fun getPrecipitationUnit(): PrecipitationUnit {
        return settings.getEnum(
            key = KEY_PRECIPITATION_UNIT,
            default = PrecipitationUnit.Millimeter,
        )
    }

    override fun setPrecipitationUnit(unit: PrecipitationUnit) {
        settings.putString(
            key = KEY_PRECIPITATION_UNIT,
            value = unit.name,
        )
    }

    override fun getPressureUnit(): PressureUnit {
        return settings.getEnum(
            key = KEY_PRESSURE_UNIT,
            default = PressureUnit.Hectopascal,
        )
    }

    override fun setPressureUnit(unit: PressureUnit) {
        settings.putString(
            key = KEY_PRESSURE_UNIT,
            value = unit.name,
        )
    }

    override fun getDistanceUnit(): DistanceUnit {
        return settings.getEnum(
            key = KEY_DISTANCE_UNIT,
            default = DistanceUnit.Kilometer,
        )
    }

    override fun setDistanceUnit(unit: DistanceUnit) {
        settings.putString(
            key = KEY_DISTANCE_UNIT,
            value = unit.name,
        )
    }

    private inline fun <reified T : Enum<T>> Settings.getEnum(
        key: String,
        default: T,
    ): T {
        val value = getStringOrNull(key) ?: return default

        return enumValues<T>()
            .firstOrNull { it.name == value }
            ?: default
    }

    private companion object {
        const val KEY_TEMPERATURE_UNIT = "temperature_unit"
        const val KEY_WIND_SPEED_UNIT = "wind_speed_unit"
        const val KEY_PRECIPITATION_UNIT = "precipitation_unit"
        const val KEY_PRESSURE_UNIT = "pressure_unit"
        const val KEY_DISTANCE_UNIT = "distance_unit"
    }
    override fun resetUnits() {
        settings.remove(KEY_TEMPERATURE_UNIT)
        settings.remove(KEY_WIND_SPEED_UNIT)
        settings.remove(KEY_PRECIPITATION_UNIT)
        settings.remove(KEY_PRESSURE_UNIT)
        settings.remove(KEY_DISTANCE_UNIT)
    }
}