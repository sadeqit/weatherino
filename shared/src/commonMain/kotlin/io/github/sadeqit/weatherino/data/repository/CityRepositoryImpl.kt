package io.github.sadeqit.weatherino.data.repository

import io.github.sadeqit.weatherino.data.model.toDomain
import io.github.sadeqit.weatherino.data.remote.api.OpenWeatherApi
import io.github.sadeqit.weatherino.domain.model.City
import io.github.sadeqit.weatherino.domain.repository.CityRepository

class CityRepositoryImpl(
    private val api: OpenWeatherApi
) : CityRepository {

    override suspend fun searchCities(
        query: String,
        limit: Int
    ): List<City> {
        return api
            .searchCities(query, limit)
            .map { it.toDomain() }
    }
}