package io.github.sadeqit.weatherino.platform.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.location.LocationSettingsRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidLocationProvider(
    context: Context
) : LocationProvider {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val settingsClient: SettingsClient =
        LocationServices.getSettingsClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10_000L
        ).build()

        val settingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        return suspendCancellableCoroutine { continuation ->

            settingsClient
                .checkLocationSettings(settingsRequest)
                .addOnSuccessListener {
                    fusedLocationClient
                        .getCurrentLocation(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            null
                        )
                        .addOnSuccessListener { androidLocation ->
                            continuation.resume(
                                androidLocation?.let {
                                    Location(
                                        latitude = it.latitude,
                                        longitude = it.longitude
                                    )
                                }
                            )
                        }
                        .addOnFailureListener {
                            continuation.resume(null)
                        }
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }
}