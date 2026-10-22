package io.github.sadeqit.weatherino.platform.location

class WebLocationProvider : LocationProvider {

    override suspend fun getCurrentLocation(): Location? {
        return null
    }
}