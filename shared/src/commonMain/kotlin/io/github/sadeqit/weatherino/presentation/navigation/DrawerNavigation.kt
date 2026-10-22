package io.github.sadeqit.weatherino.presentation.navigation

import androidx.navigation.NavController
import io.github.sadeqit.weatherino.platform.rateApp

fun handleDrawerItem(
    item: DrawerItem,
    navController: NavController,
    onExitClick: () -> Unit,
) {
    when (item) {
        DrawerItem.RateApp -> {
            rateApp()
        }

        DrawerItem.Exit -> {
            onExitClick()
        }

        else -> {
            item.route?.let { route ->
                navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    }
}