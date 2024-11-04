package com.example.opsc7312poe

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityObserver(private val context: Context) {

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    // You can add a listener to observe connectivity changes
    fun observeConnectivityChanges() {
        // Implement a method to listen for connectivity changes
        // Use LiveData or another observable pattern to notify when connectivity changes
    }
}