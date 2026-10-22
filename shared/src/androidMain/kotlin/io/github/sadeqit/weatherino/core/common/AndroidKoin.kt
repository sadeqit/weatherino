package io.github.sadeqit.weatherino.core.common

import android.content.Context
import io.github.sadeqit.weatherino.core.database.databasePlatformModule
import io.github.sadeqit.weatherino.core.di.locationModule
import io.github.sadeqit.weatherino.core.di.networkPlatformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin

fun initAndroidKoin(context: Context) {
    if (GlobalContext.getOrNull() != null) {
        return
    }

    startKoin {
        androidContext(context)

        modules(
            appModules +
                    databasePlatformModule(context) +
                    locationModule(context) +
                    networkPlatformModule
        )
    }
}