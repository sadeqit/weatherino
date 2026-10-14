package io.github.sadeqit.weatherino.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.github.sadeqit.weatherino.presentation.theme.GlassBorder
import io.github.sadeqit.weatherino.presentation.theme.GlassShape
import io.github.sadeqit.weatherino.presentation.theme.GlassWhite
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.menu
import io.github.sadeqit.weatherino.resources.search
import org.jetbrains.compose.resources.painterResource

@Composable
fun WeatherHeader(
    modifier: Modifier = Modifier,
    locationName: String,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(GlassShape)
            .background(GlassWhite)
            .border(
                width = 1.dp,
                color = GlassBorder,
                shape = GlassShape
            )
            .height(56.dp)
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = onMenuClick
        ) {
            Image(
                painter = painterResource(Res.drawable.menu),
                contentDescription = "منو",
                modifier = Modifier.size(24.dp)
            )

        }

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = locationName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1
        )

        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = onSearchClick
        ) {
            Image(
                painter = painterResource(Res.drawable.search),
                contentDescription = "جستجو",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}