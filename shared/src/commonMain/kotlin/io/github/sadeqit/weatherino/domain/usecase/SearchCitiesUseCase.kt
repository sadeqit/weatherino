package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.domain.model.City
import io.github.sadeqit.weatherino.domain.repository.CityRepository

class SearchCitiesUseCase(
    private val cityRepository: CityRepository
) {

    suspend operator fun invoke(
        query: String,
        limit: Int = 5
    ): List<City> {
        return cityRepository.searchCities(
            query = query,
            limit = limit
        )
    }
}