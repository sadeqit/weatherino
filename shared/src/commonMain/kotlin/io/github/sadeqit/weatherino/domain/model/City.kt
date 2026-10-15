package io.github.sadeqit.weatherino.domain.model

data class City(
    val name: String,
    val country: String,
    val state: String?,
    val latitude: Double,
    val longitude: Double
)