package com.example.androidfinalproject.presentation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel

@Composable
fun RootNavGraph(navController: NavHostController, viewModel: ProfileScreenViewModel) {
    NavHost(navController = navController, startDestination = Routes.Starting.route,
        route = "root"){
        startingGraph(navController)
        composable(route = Routes.BottomBar.route) {
            BottomBarNavigation(viewModel = viewModel)
        }
    }
}