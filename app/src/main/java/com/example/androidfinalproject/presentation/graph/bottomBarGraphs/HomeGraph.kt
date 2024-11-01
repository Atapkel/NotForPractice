package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.genre.GenreScreen
import com.example.androidfinalproject.presentation.movie_detail.DetailScreen
import com.example.androidfinalproject.presentation.home.HomeScreen

fun NavGraphBuilder.homeGraph (navController: NavHostController){
    navigation(route = BottomNavigationItem.Home.route, startDestination = HomeRoutes.HOME_MAIN){
        composable(route = HomeRoutes.HOME_MAIN) {
            HomeScreen(navController)
        }
        composable(route = HomeRoutes.HOME_DETAIL+"/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            DetailScreen(id)
        }
        composable(route = HomeRoutes.HOME_SEE_ALL+"/{type}",
            arguments = listOf(
                navArgument("type"){
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val type = entry.arguments?.getString("type") ?: ""
            GenreScreen(type)
        }

    }
}

object HomeRoutes{
    const val HOME_MAIN = "home_main_screen"
    const val HOME_DETAIL = "home_detail_screen"
    const val HOME_SEE_ALL = "home_see_screen"
}