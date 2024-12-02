package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.search.SearchScreen


fun NavGraphBuilder.searchGraph (navController: NavHostController){
    navigation(route = BottomNavigationItem.Search.route, startDestination = SearchRoutes.SEARCH) {
        composable(route = SearchRoutes.SEARCH) {
//            SearchScreen(navController, filtersViewModel)
        }
        composable(route = SearchRoutes.FILTER) {
//            FilmFiltersScreen(navController)
        }
        composable(route = SearchRoutes.COUNTRY) {
//            CountryPage(navController)
        }
        composable(route = SearchRoutes.DATE) {
//            CountryPage(navController)
        }
    }
}


object SearchRoutes{
    const val SEARCH = "search"
    const val DATE = "date"
    const val GENRE = "genre"
    const val FILTER = "filter"
    const val COUNTRY = "country"
}