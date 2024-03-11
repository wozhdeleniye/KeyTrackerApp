package com.example.keytrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.filmushits.Network.Network
import com.example.filmushits.Network.TokenManager
import com.example.keytrackerapp.Navigation.Nav
import com.example.keytrackerapp.View.LogRegScreen.LogScreen
import com.example.keytrackerapp.View.LogRegScreen.RegScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Network
            .getInstance()
            .setTokenManager(TokenManager(applicationContext))
        Network.getInstance().initialize()
        setContent {
            Nav()
        }
    }
}

