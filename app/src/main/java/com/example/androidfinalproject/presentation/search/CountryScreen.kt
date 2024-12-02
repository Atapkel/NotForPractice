package com.example.androidfinalproject.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.presentation.search.components.CountryPage

@Composable
fun CountryScreen(navController: NavHostController, viewModel: FilmFiltersViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is FilmFiltersState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is FilmFiltersState.Success -> {
            val filters = (state as FilmFiltersState.Success).filters
            CountryPage(filters.countries,navController,viewModel)
        }
        is FilmFiltersState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (state as FilmFiltersState.Error).message, color = Color.Red)
            }
        }
    }
}