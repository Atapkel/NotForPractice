package com.example.androidfinalproject.presentation.graph.bottomBarGraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.actor.ActorScreen
import com.example.androidfinalproject.presentation.genre.GenreScreen
import com.example.androidfinalproject.presentation.movie_detail.DetailScreen
import com.example.androidfinalproject.presentation.home.HomeScreen
import com.example.androidfinalproject.presentation.movie_detail.component.FilmImage
import com.example.androidfinalproject.presentation.movie_detail.component.FilmImagesScreen

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(route = BottomNavigationItem.Home.route, startDestination = HomeRoutes.HOME_MAIN) {
        composable(route = HomeRoutes.HOME_MAIN) {
            HomeScreen(path = { route -> navController.navigate(route) })
        }
        composable(route = HomeRoutes.HOME_DETAIL + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            DetailScreen(id, path = { route -> navController.navigate(route) })
        }
        composable(route = HomeRoutes.HOME_SEE_ALL + "/{type}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val type = entry.arguments?.getString("type") ?: ""
            GenreScreen(type, path = { route -> navController.navigate(route) })
        }
        composable(route = HomeRoutes.FILM_IMAGES + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            FilmImage(id, path = { route -> navController.navigate(route) })
        }
        composable(
            route = HomeRoutes.ACTOR_INFO + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            ActorScreen(
                id = id,
                path = { route ->
                    if (route == "Back") {
                        navController.popBackStack()
                    } else {
                        navController.navigate(route)
                    }
                }
            )
        }

    }
}

object HomeRoutes {
    const val FILM_IMAGES = "film_images"
    const val HOME_MAIN = "home_main_screen"
    const val HOME_DETAIL = "home_detail_screen"
    const val HOME_SEE_ALL = "home_see_screen"
    const val ACTOR_INFO = "actor_info"
}