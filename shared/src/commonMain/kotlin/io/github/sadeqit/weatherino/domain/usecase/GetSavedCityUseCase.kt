package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.data.local.CityPreferences

class GetSavedCityUseCase(
    private val cityPreferences: CityPreferences
) {
    operator fun invoke(): SavedCity? {
        if (!cityPreferences.hasSavedCity()) {
            return null
        }

        return SavedCity(
            name = cityPreferences.getCityName()!!,
            latitude = cityPreferences.getLatitude()!!,
            longitude = cityPreferences.getLongitude()!!
        )
    }
}

data class SavedCity(
    val name: String,
    val latitude: Double,
    val longitude: Double
)