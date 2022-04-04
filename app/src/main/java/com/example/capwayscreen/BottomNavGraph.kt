package com.example.capwayscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capwayscreen.ui.screens.*


// This class defines tha application navigation routes that are used by the
// Bottom App Bar.
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.AccountScreen.route
    ) {
        composable(route = BottomBarScreen.AccountScreen.route) {
            AccountScreen()
        }
        composable(route = BottomBarScreen.SendScreen.route) {
            SendScreen()
        }
        composable(route = BottomBarScreen.MoreScreen.route) {
            MoreScreen()
        }
        composable(route = BottomBarScreen.ContentScreen.route) {
            ContentScreen()
        }
        composable(route = BottomBarScreen.PhundsScreen.route) {
            PhundsScreen()
        }
    }
}