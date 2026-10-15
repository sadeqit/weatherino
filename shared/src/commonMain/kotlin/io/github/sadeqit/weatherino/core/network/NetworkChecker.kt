package io.github.sadeqit.weatherino.core.network

expect class NetworkChecker {

    suspend fun isAvailable(): Boolean
}