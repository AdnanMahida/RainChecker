package com.ad.rainchecker.utility.helper

import android.content.Context
import android.location.Geocoder
import android.os.Build
import java.util.Locale


fun getCityNameFromLocation(context: Context, location: android.location.Location): String? {
    return try {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            geocoder.getFromLocation(location.latitude, location.longitude, 1)
        } else {
            null // need to look up
        }

        if (!addresses.isNullOrEmpty()) {
            addresses.firstOrNull()?.locality ?: addresses.firstOrNull()?.subAdminArea
            ?: addresses.firstOrNull()?.adminArea
        } else {
            null
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
