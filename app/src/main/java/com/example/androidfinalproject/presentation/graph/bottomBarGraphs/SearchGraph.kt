package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.search.SearchScreen


fun NavGraphBuilder.searchGraph (navController: NavHostController){
    navigation(route = BottomNavigationItem.Profile.route, startDestination = "search") {
        composable(route = "search") {
            SearchScreen()
        }
    }
}