package io.github.sadeqit.weatherino.presentation.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
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
    isSearchActive: Boolean,
    searchQuery: String,
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchClose: () -> Unit,
    onLocationClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    LaunchedEffect(isSearchActive) {
        if (isSearchActive) {
            focusRequester.requestFocus()
        }
    }

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
        if (isSearchActive) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 4.dp)
                    .focusRequester(focusRequester),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Right
                ),
                placeholder = {
                    Text(
                        text = "جستجوی شهر",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right,
                        color = Color.White.copy(alpha = 0.55f)
                    )
                },

                leadingIcon = {
                    IconButton(
                        onClick = onLocationClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "استفاده از موقعیت فعلی",
                            tint = Color.White
                        )
                    }
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()
                            onSearchClose()
                        }
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.search),
                            contentDescription = "بستن جستجو",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp)
            )
        } else {
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
}
