package com.example.keytrackerapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keytrackerapp.View.LogRegScreen.LogScreen
import com.example.keytrackerapp.View.LogRegScreen.RegScreen

@Composable
fun Nav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LogScreen"){
        composable(route = "LogScreen"){
            LogScreen(navController)
        }
        composable(route = "RegScreen"){
            RegScreen(navController)
        }
        composable(route = "AppScreen"){
            AppNav()
        }
    }
}