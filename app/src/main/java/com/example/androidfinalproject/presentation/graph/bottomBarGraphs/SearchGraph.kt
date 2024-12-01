package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.search.FilterScreen
import com.example.androidfinalproject.presentation.search.SearchScreen


fun NavGraphBuilder.searchGraph (navController: NavHostController){
    navigation(route = BottomNavigationItem.Search.route, startDestination = SearchRoutes.SEARCH) {
        composable(route = SearchRoutes.SEARCH) {
            SearchScreen(navController)
        }
        composable(route = SearchRoutes.FILTER) {
            FilterScreen(navController)
        }
    }
}


object SearchRoutes{
    const val SEARCH = "search"
    const val FILTER = "filter"
}