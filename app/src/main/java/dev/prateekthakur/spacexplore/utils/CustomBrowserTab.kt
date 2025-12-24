package dev.prateekthakur.spacexplore.utils

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun openExternalLink(context: Context, url: String) {
    CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setInstantAppsEnabled(true)
        .build()
        .launchUrl(context, Uri.parse(url))
}