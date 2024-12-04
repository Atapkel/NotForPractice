package com.example.androidfinalproject.presentation.graph

sealed class Routes(val route: String) {
    object BottomBar: Routes("home_screen")
    object Starting: Routes("onboarding_screen")
}