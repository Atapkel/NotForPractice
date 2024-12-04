package com.example.androidfinalproject.presentation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.androidfinalproject.MainActivity
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.homeGraph
import com.example.androidfinalproject.presentation.profile.ListOfMoviesScreen
import com.example.androidfinalproject.presentation.profile.MovieDetailSavedScreen
import com.example.androidfinalproject.presentation.profile.ProfileScreen
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel
import com.example.androidfinalproject.presentation.search.SearchScreen


@Composable
fun BottomBarGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: ProfileScreenViewModel
) {


    NavHost(
        navController = navController,
        route = Routes.BottomBar.route,
        startDestination = BottomNavigationItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        homeGraph(navController, viewModel)

        composable(route = BottomNavigationItem.Profile.route) {
            ProfileScreen(viewModel, path = { route -> navController.navigate(route) })
        }
        composable(route = ProfileRoutes.MOVIES_BY_TYPE + "/{collectionName}",
            arguments = listOf(
                navArgument("collectionName") {
                    type = NavType.StringType
                }
            )) { entry ->
            val type = entry.arguments?.getString("collectionName") ?: ""
//            println("from navigation $type")
            ListOfMoviesScreen(
                viewModel = viewModel,
                type = type,
                path = { route ->
                    if (route.equals("popBackStack"))navController.popBackStack()
                    else navController.navigate(route) })
        }
        composable(route = ProfileRoutes.DETAIL_OF_SAVED_MOVIE + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0
            println("from navigation $id")
            MovieDetailSavedScreen(viewModel, id, path = { route ->
                if (route.equals("popBackStack")) {
                    navController.popBackStack()
                } else {
                    navController.navigate(route)
                }
            })
        }

        composable(route = BottomNavigationItem.Search.route) {
//            SearchScreen()
        }

    }
}

object ProfileRoutes {
    const val MOVIES_BY_TYPE = "tpye_movies"
    const val DETAIL_OF_SAVED_MOVIE = "saved_movie"
}