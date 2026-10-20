package io.github.sadeqit.weatherino.platform.app

import android.app.Activity

class AndroidAppExitHandler(
    private val activity: Activity
) : AppExitHandler {

    override fun exit() {
        activity.finishAffinity()
    }
}