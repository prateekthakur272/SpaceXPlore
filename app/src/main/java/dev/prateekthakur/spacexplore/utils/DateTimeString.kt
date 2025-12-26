package dev.prateekthakur.spacexplore.utils

import android.text.format.DateFormat

fun Long.formatDate(format: String = "dd MMM, yyyy") : String{
    return DateFormat.format(format, this).toString()
}

fun Long.formatTime(format: String = "hh:mm a zz") : String{
    return DateFormat.format(format, this).toString()
}