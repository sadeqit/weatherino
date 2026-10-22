package io.github.sadeqit.weatherino.core.common

import io.github.sadeqit.weatherino.core.network.NetworkChecker
import io.github.sadeqit.weatherino.data.local.DatabaseDriverFactory
import io.github.sadeqit.weatherino.platform.app.AppExitHandler
import io.github.sadeqit.weatherino.platform.app.JvmAppExitHandler
import io.github.sadeqit.weatherino.platform.location.JvmLocationProvider
import io.github.sadeqit.weatherino.platform.location.LocationProvider
import org.koin.dsl.module

actual val platformModules = listOf(
    module {
        single<NetworkChecker> {
            NetworkChecker()
        }

        single<DatabaseDriverFactory> {
            DatabaseDriverFactory()
        }

        single<LocationProvider> {
            JvmLocationProvider()
        }

        single<AppExitHandler> {
            JvmAppExitHandler(
                onExit = get()
            )
        }
    }
)