package io.github.sadeqit.weatherino.data.model

import io.github.sadeqit.weatherino.data.remote.model.GeocodingResponse
import io.github.sadeqit.weatherino.domain.model.City

fun GeocodingResponse.toDomain(): City {
    return City(
        name = name.toIranianCityName(),
        country = country,
        state = state,
        latitude = lat,
        longitude = lon
    )
}