package io.github.sadeqit.weatherino.presentation.navigation

import io.github.sadeqit.weatherino.resources.Res
import io.github.sadeqit.weatherino.resources.about
import io.github.sadeqit.weatherino.resources.contact
import io.github.sadeqit.weatherino.resources.exit
import io.github.sadeqit.weatherino.resources.rate_app
import io.github.sadeqit.weatherino.resources.settings
import io.github.sadeqit.weatherino.resources.support
import org.jetbrains.compose.resources.DrawableResource


sealed class DrawerItem(
    val title: String,
    val icon: DrawableResource,
    val route: Any? = null,
) {
    data object Settings : DrawerItem(
        title = "تنظیمات",
        icon = Res.drawable.settings,
        route = SettingsRoute,
    )

    data object RateApp : DrawerItem(
        title = "نظر دادن به برنامه",
        icon = Res.drawable.rate_app,
    )

    data object Support : DrawerItem(
        title = "حمایت از من",
        icon = Res.drawable.support,
        route = SupportRoute,
    )

    data object Contact : DrawerItem(
        title = "ارتباط با ما",
        icon = Res.drawable.contact,
        route = ContactRoute,
    )

    data object About : DrawerItem(
        title = "درباره ودرینو",
        icon = Res.drawable.about,
        route = AboutRoute,
    )

    data object Exit : DrawerItem(
        title = "خروج از برنامه",
        icon = Res.drawable.exit,
    )
}