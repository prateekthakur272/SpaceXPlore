package dev.prateekthakur.spacexplore.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


fun openExternalLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}