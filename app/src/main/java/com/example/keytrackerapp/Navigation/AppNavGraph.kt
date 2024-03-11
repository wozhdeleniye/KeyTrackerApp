package com.example.keytrackerapp.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.keytrackerapp.View.AppScreen.AddRequestScreen
import com.example.keytrackerapp.View.AppScreen.DetailsScreen
import com.example.keytrackerapp.View.AppScreen.UserProfile.ProfileScreen
import com.example.keytrackerapp.View.AppScreen.RequestList.RequestListScreen
import com.example.keytrackerapp.View.AppScreen.Transit.TransitAcception
import com.example.keytrackerapp.View.AppScreen.Transit.TransitOfferScreen
import com.example.keytrackerapp.View.AppScreen.Transit.TransitRequestsScreen

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
        composable(route = "DetailsScreen/{id}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ){backStackEntry ->
            DetailsScreen(navController, backStackEntry.arguments?.getString("id")!!)
        }
        composable(route = "TransitScreen/{id}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ){backStackEntry ->
            TransitOfferScreen(navController, backStackEntry.arguments?.getString("id")!!)
        }
        composable(route = "TransitRequests"){
            TransitRequestsScreen(navController)
        }
        composable(route = "TransitAccept/{id}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ){backStackEntry ->
            TransitAcception(navController, backStackEntry.arguments?.getString("id")!!)
        }
    }
}