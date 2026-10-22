package io.github.sadeqit.weatherino.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String? = null
)