package com.example.androidfinalproject.presentation.graph

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel


@Composable
fun BottomBarNavigation(navController: NavHostController = rememberNavController(), viewModel: ProfileScreenViewModel) {
    Scaffold(bottomBar = { BottomBar(navController) })
    { paddingValues ->
        BottomBarGraph(navController = navController, paddingValues, viewModel)
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Profile
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(index == selectedItemIndex,
                onClick = {

                    if (navController.currentDestination?.route != item.route) {
                        selectedItemIndex = index
                        navController.navigate(item.route) {
                            launchSingleTop = true
                        }
                    }

                },
                icon = {
                    Icon(
                        painter = painterResource(item.selectedIcon),
                        contentDescription = item.route,
                        tint = if (selectedItemIndex == index) Color(0xFF3D3BFF)
                        else Color(0xFF272727)
                    )
                }
            )
        }
    }
}