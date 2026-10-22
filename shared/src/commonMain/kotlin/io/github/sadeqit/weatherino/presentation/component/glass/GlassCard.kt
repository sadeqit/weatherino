package io.github.sadeqit.weatherino.presentation.component.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.theme.GlassShape
import io.github.sadeqit.weatherino.presentation.theme.GlassWhite

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = GlassWhite,
                shape = GlassShape
            )
            .border(
                width = 1.dp,
                color = GlassBorder,
                shape = GlassShape
            )
            .padding(16.dp)
    ) {
        content()
    }
}