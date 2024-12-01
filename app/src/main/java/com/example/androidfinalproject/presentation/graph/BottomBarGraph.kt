package com.example.androidfinalproject.presentation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidfinalproject.MainActivity
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.SearchRoutes
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.homeGraph
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.profileGraph
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.searchGraph
import com.example.androidfinalproject.presentation.profile.ProfileScreen
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel
import com.example.androidfinalproject.presentation.search.FilterScreen
import com.example.androidfinalproject.presentation.search.SearchScreen


@Composable
fun BottomBarGraph(navController: NavHostController, paddingValues: PaddingValues, viewModel: ProfileScreenViewModel) {


    NavHost(
        navController = navController,
        route = Routes.BottomBar.route,
        startDestination = BottomNavigationItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        homeGraph(navController, viewModel)
        composable(route = BottomNavigationItem.Profile.route) {
            ProfileScreen(viewModel)
        }
        composable(route = BottomNavigationItem.Search.route) {
            SearchScreen(navController)
        }
//        IT SHOULD BE LIKE THIS searchGraph -> composable(FilterScreen)
        composable(route = SearchRoutes.FILTER) {
            FilterScreen(navController)
        }

    }
}