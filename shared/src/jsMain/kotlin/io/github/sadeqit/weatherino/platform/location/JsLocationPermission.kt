package io.github.sadeqit.weatherino.platform.location

import androidx.compose.runtime.Composable

@Composable
actual fun rememberLocationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit {
    return {
        onResult(false)
    }
}