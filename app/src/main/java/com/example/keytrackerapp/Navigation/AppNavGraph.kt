package com.example.keytrackerapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keytrackerapp.View.AppScreen.AddRequestScreen
import com.example.keytrackerapp.View.AppScreen.DetailsScreen
import com.example.keytrackerapp.View.AppScreen.ProfileScreen
import com.example.keytrackerapp.View.AppScreen.RequestListScreen
import com.example.keytrackerapp.View.LogRegScreen.LogScreen
import com.example.keytrackerapp.View.LogRegScreen.RegScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "AppScreen"){
        composable(route = "AppScreen"){
            RequestListScreen(navController)
        }
        composable(route = "AddRequestScreen"){
            AddRequestScreen(navController)
        }
        composable(route = "ProfileScreen"){
            ProfileScreen(navController)
        }
        composable(route = "DetailsScreen"){
            DetailsScreen(navController)
        }
    }
}