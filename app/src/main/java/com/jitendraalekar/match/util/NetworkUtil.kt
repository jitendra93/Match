package com.jitendraalekar.match.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.flow

fun Context.isNetworkConnected(): Boolean {
    return   (getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let {
           val capabilities = it.getNetworkCapabilities(it.activeNetwork)
           capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ||
                   capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
                   capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true
       } ?: false


}