package id.maskology.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object LocalDateConverter {
    val timeStamp: String = SimpleDateFormat("dd-MMM-yyy", Locale.US).format(System.currentTimeMillis())
    @SuppressLint("SimpleDateFormat")
    fun convertLocalDate(date: String): String? {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("EEE, MMM d yyyy, HH:mm")
        return parser.parse(date)?.let { formatter.format(it).toString() }
    }
}