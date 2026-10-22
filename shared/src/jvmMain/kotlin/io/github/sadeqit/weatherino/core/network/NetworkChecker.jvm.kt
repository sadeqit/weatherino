package io.github.sadeqit.weatherino.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URI

actual class NetworkChecker {

    actual suspend fun isAvailable(): Boolean {
        return try {
            val connection = withContext(Dispatchers.IO) {
                URI("https://www.google.com")
                    .toURL()
                    .openConnection()
            } as HttpURLConnection

            connection.connectTimeout = 3000
            connection.readTimeout = 3000
            connection.requestMethod = "HEAD"

            withContext(Dispatchers.IO) {
                connection.connect()
            }

            connection.responseCode in 200..399
        } catch (_: Exception) {
            false
        }
    }
}