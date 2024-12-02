package com.example.androidfinalproject.presentation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.SearchRoutes
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.homeGraph
import com.example.androidfinalproject.presentation.profile.ProfileScreen
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel
import com.example.androidfinalproject.presentation.search.CountryScreen
import com.example.androidfinalproject.presentation.search.FilmFiltersViewModel
import com.example.androidfinalproject.presentation.search.FilterGenreScreen
import com.example.androidfinalproject.presentation.search.SearchScreen
import com.example.androidfinalproject.presentation.search.components.DataPage
import com.example.androidfinalproject.presentation.search.components.FilterPage


@Composable
fun BottomBarGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: ProfileScreenViewModel
) {

    val filtersViewModel = FilmFiltersViewModel()
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
            SearchScreen(navController, filtersViewModel)
        }
//        IT SHOULD BE IN searchGraph
        composable(route = SearchRoutes.FILTER) {
            FilterPage(navController, filtersViewModel)
        }
        composable(route = SearchRoutes.COUNTRY) {
            CountryScreen(navController, filtersViewModel)
        }
        composable(route = SearchRoutes.GENRE) {
            FilterGenreScreen(navController, filtersViewModel)
        }
        composable(route = SearchRoutes.DATE) {
            DataPage(navController, filtersViewModel)
        }
    }
}

