package com.example.keytrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.keytrackerapp.Navigation.Nav
import com.example.keytrackerapp.View.LogRegScreen.LogScreen
import com.example.keytrackerapp.View.LogRegScreen.RegScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nav()
        }
    }
}

