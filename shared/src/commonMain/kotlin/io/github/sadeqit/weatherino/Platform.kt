package io.github.sadeqit.weatherino

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform