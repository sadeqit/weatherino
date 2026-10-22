package io.github.sadeqit.weatherino.domain.repository

import io.github.sadeqit.weatherino.domain.model.City

interface CityStorage {

    suspend fun saveCity(city: City)

    suspend fun getCity(): City?

    suspend fun clearCity()
}