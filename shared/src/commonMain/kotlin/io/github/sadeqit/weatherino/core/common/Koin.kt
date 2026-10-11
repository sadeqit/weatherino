package io.github.sadeqit.weatherino.core.common

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModules)
    }
}