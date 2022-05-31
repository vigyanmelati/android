package id.maskology.utils

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

object NetworkCheck {
    private const val TAG = "NETWORK: "

    @Suppress("DEPRECATION")
    fun connectionCheck(context: Context): Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectionManager.activeNetworkInfo
        if (activeNetworkInfo != null) {
            Log.d(TAG, "Connected")
            return true
        }
        Log.d(TAG, "Disconnected")
        return false
    }
}
