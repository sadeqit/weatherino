package io.github.sadeqit.weatherino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.sadeqit.weatherino.core.common.initAndroidKoin
import io.github.sadeqit.weatherino.platform.appContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContext = this
        initAndroidKoin(this)

        setContent {
            App(
                onExit = {
                    finishAffinity()
                }
            )
        }
    }
}
