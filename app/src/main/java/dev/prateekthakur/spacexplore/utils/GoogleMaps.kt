package dev.prateekthakur.spacexplore.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


fun openInGoogleMaps(context: Context, lat: Double, lng: Double) {
    val uri = Uri.parse("geo:$lat,$lng?q=$lat,$lng")
    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        setPackage("com.google.android.apps.maps")
    }
    context.startActivity(intent)
}