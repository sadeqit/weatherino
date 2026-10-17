package io.github.sadeqit.weatherino.presentation.component.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import io.github.sadeqit.weatherino.presentation.theme.WeatherBlue
import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.icon

@Composable
fun WeatherDrawerContent(
    currentRoute: String?,
    onSettingsClick: () -> Unit,
    onRateClick: () -> Unit,
    onSupportClick: () -> Unit,
    onContactClick: () -> Unit,
    onAboutClick: () -> Unit,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val glassShape = RoundedCornerShape(24.dp)

    ModalDrawerSheet(
        modifier = modifier
            .padding(8.dp)
            .clip(glassShape)
            .background(
                color = WeatherBlue.copy(alpha = 0.35f),
                shape = glassShape,
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.20f),
                shape = glassShape,
            ),
        drawerContainerColor = Color.Transparent,
        drawerContentColor = Color.White,
        drawerShape = glassShape,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            // Header
            Image(
                painter = painterResource(Res.drawable.icon),
                contentDescription = "Weatherino",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Settings
            NavigationDrawerItem(
                label = {
                    Text("تنظیمات")
                },
                selected = currentRoute == "settings",
                onClick = onSettingsClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "تنظیمات",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.White.copy(alpha = 0.15f),
                    unselectedContainerColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )

            // Rate
            NavigationDrawerItem(
                label = {
                    Text("نظر دادن به برنامه")
                },
                selected = false,
                onClick = onRateClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "نظر دادن به برنامه",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )

            // Support
            NavigationDrawerItem(
                label = {
                    Text("حمایت از من")
                },
                selected = false,
                onClick = onSupportClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "حمایت از من",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )

            // Contact
            NavigationDrawerItem(
                label = {
                    Text("ارتباط با ما")
                },
                selected = false,
                onClick = onContactClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "ارتباط با ما",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )

            // About
            NavigationDrawerItem(
                label = {
                    Text("درباره Weatherino")
                },
                selected = currentRoute == "about",
                onClick = onAboutClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "درباره Weatherino",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.White.copy(alpha = 0.15f),
                    unselectedContainerColor = Color.Transparent,
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )

            Spacer(modifier = Modifier.height(12.dp))

            androidx.compose.material3.HorizontalDivider(
                color = Color.White.copy(alpha = 0.15f),
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Exit
            NavigationDrawerItem(
                label = {
                    Text("خروج از برنامه")
                },
                selected = false,
                onClick = onExitClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "خروج از برنامه",
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )
        }
    }
}