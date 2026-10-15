package io.github.sadeqit.weatherino.data.local

import com.russhwolf.settings.Settings

class CityPreferences(
    private val settings: Settings
) {

    companion object {
        private const val KEY_CITY_NAME = "selected_city_name"
        private const val KEY_CITY_LATITUDE = "selected_city_latitude"
        private const val KEY_CITY_LONGITUDE = "selected_city_longitude"
    }

    fun saveCity(
        name: String,
        latitude: Double,
        longitude: Double
    ) {
        settings.putString(KEY_CITY_NAME, name)
        settings.putDouble(KEY_CITY_LATITUDE, latitude)
        settings.putDouble(KEY_CITY_LONGITUDE, longitude)
    }

    fun getCityName(): String? {
        return settings.getStringOrNull(KEY_CITY_NAME)
    }

    fun getLatitude(): Double? {
        return settings.getDoubleOrNull(KEY_CITY_LATITUDE)
    }

    fun getLongitude(): Double? {
        return settings.getDoubleOrNull(KEY_CITY_LONGITUDE)
    }

    fun hasSavedCity(): Boolean {
        return getCityName() != null &&
                getLatitude() != null &&
                getLongitude() != null
    }
}