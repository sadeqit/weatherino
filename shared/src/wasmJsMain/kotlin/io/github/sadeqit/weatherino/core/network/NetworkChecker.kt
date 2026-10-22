package io.github.sadeqit.weatherino.core.network

actual class NetworkChecker {
    actual suspend fun isAvailable(): Boolean = true
}