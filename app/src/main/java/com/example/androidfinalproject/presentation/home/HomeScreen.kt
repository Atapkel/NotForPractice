package com.example.androidfinalproject.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R

import com.example.androidfinalproject.presentation.home.component.GenreSection
import com.example.androidfinalproject.presentation.loadingScreen.LoadingScreen

@Composable
fun HomeScreen(path: (String) -> Unit) {
    val viewModel: HomeViewModel = remember { HomeViewModel() }

    when (val state = viewModel.state) {
        is HomeState.Initial -> {
        }
        is HomeState.Loading -> {
            LoadingScreen()
        }
        is HomeState.Success -> {
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
                items(state.data) { movies ->
                    GenreSection(movies = movies, path)
                }
            }
        }
        is HomeState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.eye_icon),
                    contentDescription = "Error",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = state.message, color = Color.Red)
            }
        }
    }
}
