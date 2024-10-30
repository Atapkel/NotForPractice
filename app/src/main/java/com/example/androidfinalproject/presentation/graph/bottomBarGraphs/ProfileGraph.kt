package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.profile.ProfileScreen


fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(route = BottomNavigationItem.Profile.route, startDestination = "profile") {
        composable(route = "profile") {
            ProfileScreen()
        }
    }
}