package io.github.sadeqit.weatherino.platform

import android.content.Intent
import androidx.core.net.toUri

actual fun openLink(url: String) {
    try {
        val intent = Intent(
            Intent.ACTION_VIEW,
            url.toUri(),
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        appContext.startActivity(intent)
    } catch (_: Exception) {
        // Link cannot be opened
    }
}