package com.example.newfilmlistapp.network.check

import android.net.ConnectivityDiagnosticsManager
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import com.example.newfilmlistapp.widget.ErrorMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckInternet(private val view: View) {

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            ErrorMessage(view).errorMessage()
        }
    }
}



//    fun check() {
//
//        CoroutineScope(Dispatchers.IO).launch {
//
//            val connectivityManager = getSystemService(ConnectivityManager::class.java)
//                connectivityManager.requestNetwork(networkRequest, networkCallback)
//
//        }
//    }

