package io.github.sadeqit.weatherino.core.di

import io.github.sadeqit.weatherino.core.network.NetworkChecker
import org.koin.dsl.module

val networkPlatformModule = module {
    single<NetworkChecker> {
        NetworkChecker(
            context = get()
        )
    }
}