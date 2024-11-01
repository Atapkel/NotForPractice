package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import HomeScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.domain.model.GenreData
import com.example.androidfinalproject.presentation.genre.GenreScreen
import com.example.androidfinalproject.presentation.movie_detail.DetailScreen

fun NavGraphBuilder.homeGraph (navController: NavHostController){
    navigation(route = BottomNavigationItem.Home.route, startDestination = HomeRoutes.HOME_MAIN){
        composable(route = HomeRoutes.HOME_MAIN) {
            HomeScreen(GenreData.genres, navController)
        }
        composable(route = HomeRoutes.HOME_DETAIL) {
            DetailScreen()
        }
        composable(route = HomeRoutes.HOME_SEE_ALL) {
            GenreScreen()
        }

    }
}

object HomeRoutes{
    const val HOME_MAIN = "home_main_screen"
    const val HOME_DETAIL = "home_detail_screen"
    const val HOME_SEE_ALL = "home_see_screen"
}