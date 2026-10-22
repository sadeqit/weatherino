package io.github.sadeqit.weatherino.platform

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

lateinit var appContext: Context

actual fun rateApp() {
    val packageName = appContext.packageName

    try {
        val bazaarIntent = Intent(Intent.ACTION_EDIT).apply {
            data = "bazaar://details?id=$packageName".toUri()
            setPackage("com.farsitel.bazaar")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        if (bazaarIntent.resolveActivity(appContext.packageManager) != null) {
            appContext.startActivity(bazaarIntent)
        } else {
            openWebFallback(packageName)
        }
    } catch (e: Exception) {
        openWebFallback(packageName)
    }
}

private fun openWebFallback(packageName: String) {
    val webIntent = Intent(
        Intent.ACTION_VIEW,
        "https://cafebazaar.ir/app/$packageName".toUri()
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    appContext.startActivity(webIntent)
}