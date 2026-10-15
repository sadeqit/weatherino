package io.github.sadeqit.weatherino

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.sadeqit.weatherino.presentation.navigation.AppNavigation
import io.github.sadeqit.weatherino.presentation.theme.WeatherinoTheme

@Composable
fun App() {
    WeatherinoTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AppNavigation()
        }
    }
}