package io.github.sadeqit.weatherino.domain.usecase

import io.github.sadeqit.weatherino.core.network.NetworkChecker

class CheckNetworkConnectionUseCase(
    private val networkChecker: NetworkChecker
) {
    suspend operator fun invoke(): Boolean {
        return networkChecker.isAvailable()
    }
}