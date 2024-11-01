package com.example.androidfinalproject.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R

import com.example.androidfinalproject.presentation.home.component.GenreSection

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val modelView = viewModel<HomeViewModel>()
    val state = modelView.state


    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(36.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(26.dp),

        ) {
        item {
            Spacer(Modifier.height(55.dp))
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Spacer(Modifier.height(47.dp))
        }
        items(state.types) { movies ->
            GenreSection(movies = movies, navController = navController)
        }
    }
}
