package io.github.sadeqit.weatherino.presentation.screen.contact

import org.jetbrains.compose.resources.DrawableResource

data class ContactMethod(
    val title: String,
    val subtitle: String,
    val icon: DrawableResource,
    val uri: String,
)