import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.kotlinSerialization)
}
val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

val openWeatherApiKey = localProperties.getProperty("OPENWEATHER_API_KEY")
    ?: error("OPENWEATHER_API_KEY is missing in local.properties")

buildkonfig {
    packageName = "io.github.sadeqit.weatherino"

    defaultConfigs {
        buildConfigField(
            com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING,
            "OPENWEATHER_API_KEY",
            openWeatherApiKey
        )
    }
}
compose.resources {
    publicResClass = true
    packageOfResClass = "io.github.sadeqit.weatherino.resources"
}
kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }
    }

    sqldelight {
        databases {
            create("WeatherinoDatabase") {
                packageName.set("io.github.sadeqit.weatherino.database")
                generateAsync.set(true)
            }
        }
    }

    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    android {
        namespace = "io.github.sadeqit.weatherino.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_21
        }

        androidResources {
            enable = true
        }

        withHostTest {
            isIncludeAndroidResources = true
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    sourceSets {

        commonMain.dependencies {

            implementation(libs.multiplatform.settings.no.arg)
            implementation(libs.material.icons.extended)

            // Compose
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)

            // Lifecycle / ViewModel
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Coroutines
            implementation(libs.kotlinx.coroutines)

            // Serialization
            implementation(libs.kotlinx.serialization.json)

            // Date & Time
            implementation(libs.kotlinx.datetime)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.engine.defaults)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Navigation
            implementation(libs.navigation.compose)

            // SQLDelight
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)

            // Multiplatform Settings
            implementation(libs.multiplatform.settings.core)

            // Image Loading
            implementation(libs.coil.compose)

            // Logging
            implementation(libs.kermit)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.play.services.location)
            implementation(libs.compose.uiTooling)
            implementation(libs.sqldelight.android.driver)
        }

        jvmMain.dependencies {
            implementation(libs.sqldelight.sqlite.driver)
        }

        jsMain.dependencies {
            implementation(libs.wrappers.browser)
            implementation(libs.sqldelight.web.worker.driver)
        }

        wasmJsMain.dependencies {
            implementation(libs.sqldelight.web.worker.driver)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}