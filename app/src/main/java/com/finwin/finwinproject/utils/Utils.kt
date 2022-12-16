package com.finwin.finwinproject.utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class Utils(private val context: Activity) {
    init {

    }

    companion object {
        fun isNetworkConnected(application: Application): Boolean {
            val cm =
                application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm != null) {
                if (Build.VERSION.SDK_INT < 23) {
                    val ni = cm.activeNetworkInfo
                    if (ni != null) {
                        return ni.isConnected && (ni.type == ConnectivityManager.TYPE_WIFI || ni.type == ConnectivityManager.TYPE_MOBILE)
                    }
                } else {
                    val n = cm.activeNetwork
                    if (n != null) {
                        val nc = cm.getNetworkCapabilities(n)
                        return nc!!.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(
                            NetworkCapabilities.TRANSPORT_WIFI
                        ) || nc.hasTransport(
                            NetworkCapabilities.TRANSPORT_VPN
                        )
                    }
                }
            }
            return false
        }
    }



}
