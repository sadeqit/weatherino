package io.github.sadeqit.weatherino.platform.app

class JvmAppExitHandler(
    private val onExit: () -> Unit
) : AppExitHandler {

    override fun exit() {
        onExit()
    }
}