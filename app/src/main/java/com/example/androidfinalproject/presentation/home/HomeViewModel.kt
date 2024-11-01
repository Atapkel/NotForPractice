package com.example.androidfinalproject.presentation.home

import Movie
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf<HomeState>(HomeState.Initial)
        private set

    private val titles = listOf("TV_SHOW", "FILM", "TV_SERIES", "MINI_SERIES")

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        state = HomeState.Loading
        viewModelScope.launch {
            try {
                val moviesByType = titles.map { type ->
                    val response = repository.getMovies(type)
                    response.body()?.items ?: emptyList()
                }
                state = HomeState.Success(moviesByType)
            } catch (e: Exception) {
                state = HomeState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}