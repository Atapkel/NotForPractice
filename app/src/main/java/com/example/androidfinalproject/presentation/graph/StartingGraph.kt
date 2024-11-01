package com.example.androidfinalproject.presentation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.androidfinalproject.presentation.loadingScreen.LoadingScreen
import com.example.androidfinalproject.presentation.onBoarding.OnBoardingScreen

fun NavGraphBuilder.startingGraph(navController: NavController) {

    navigation(route = Routes.Starting.route, startDestination = StartRoutes.ONBOARD) {
        composable(route = StartRoutes.ONBOARD) {
            OnBoardingScreen(onClick = { navController.navigate(Routes.BottomBar.route) })
        }
    }
}

object StartRoutes {
    const val ONBOARD = "onboard_screen"
}