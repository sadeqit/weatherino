package io.github.sadeqit.weatherino.domain.repository

import io.github.sadeqit.weatherino.domain.model.City

interface CityRepository {

    suspend fun searchCities(
        query: String,
        limit: Int = 5
    ): List<City>
}