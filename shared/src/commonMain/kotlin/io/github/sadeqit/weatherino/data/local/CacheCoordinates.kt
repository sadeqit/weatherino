package io.github.sadeqit.weatherino.data.local

import kotlin.math.round

internal fun Double.toCacheKey(): Double {
    return round(this * 100) / 100
}