package io.github.sadeqit.weatherino.core.common

import io.github.sadeqit.weatherino.core.network.NetworkChecker
import org.koin.dsl.module

actual val platformModules = listOf(
    module {
        single {
            NetworkChecker()
        }
    }
)