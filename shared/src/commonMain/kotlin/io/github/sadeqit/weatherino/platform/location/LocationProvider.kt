package io.github.sadeqit.weatherino.platform.location

interface LocationProvider {

    suspend fun getCurrentLocation(): Location?
}