package io.github.sadeqit.weatherino.platform.location

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

@Composable
actual fun rememberLocationPermissionRequester(
    onResult: (Boolean) -> Unit
): () -> Unit {

    val context = LocalContext.current

    val locationSettingsLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            onResult(result.resultCode == android.app.Activity.RESULT_OK)
        }

    val checkLocationSettings: () -> Unit = {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10_000L
        ).build()

        val settingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        LocationServices
            .getSettingsClient(context)
            .checkLocationSettings(settingsRequest)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    val intentSenderRequest = IntentSenderRequest.Builder(
                        exception.resolution
                    ).build()

                    locationSettingsLauncher.launch(intentSenderRequest)
                } else {
                    onResult(false)
                }
            }
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            val granted =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                        permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            if (granted) {
                checkLocationSettings()
            } else {
                onResult(false)
            }
        }

    return remember(permissionLauncher) {
        {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }
}