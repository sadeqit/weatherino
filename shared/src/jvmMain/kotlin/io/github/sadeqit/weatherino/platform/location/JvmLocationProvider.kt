package io.github.sadeqit.weatherino.platform.location

class JvmLocationProvider : LocationProvider {

    override suspend fun getCurrentLocation(): Location? {
        return null
    }
}