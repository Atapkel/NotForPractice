package com.example.androidfinalproject.presentation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.homeGraph
import com.example.androidfinalproject.presentation.profile.ProfileScreen
import com.example.androidfinalproject.presentation.search.SearchScreen


@Composable
fun BottomBarGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = Routes.BottomBar.route,
        startDestination = BottomNavigationItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        homeGraph(navController)

        composable(route = BottomNavigationItem.Profile.route) {
            ProfileScreen()
        }

        composable(route = BottomNavigationItem.Search.route) {
            SearchScreen()
        }

    }
}