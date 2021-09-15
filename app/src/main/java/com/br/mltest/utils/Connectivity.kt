package com.br.mltest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

open class Connectivity {

    /**
     * Return whether network is connected.
     *
     * Must hold `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`
     *
     * @return `true`: connected<br></br>`false`: disconnected
     */
    fun isConnected(context: Context): Boolean {
        val info: NetworkInfo? = getActiveNetworkInfo(context)
        return info != null && info.isConnected
    }

    private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

}