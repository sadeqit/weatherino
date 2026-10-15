package io.github.sadeqit.weatherino.platform.location

import androidx.compose.runtime.Composable

@Composable
expect fun rememberLocationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit