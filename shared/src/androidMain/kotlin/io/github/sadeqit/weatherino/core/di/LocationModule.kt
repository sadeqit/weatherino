package io.github.sadeqit.weatherino.core.di

import android.content.Context
import io.github.sadeqit.weatherino.platform.location.AndroidLocationProvider
import io.github.sadeqit.weatherino.platform.location.LocationProvider
import org.koin.dsl.module

fun locationModule(context: Context) = module {
    single<LocationProvider> {
        AndroidLocationProvider(context)
    }
}