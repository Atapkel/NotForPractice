package com.example.androidfinalproject.presentation.home

import Movie
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf(ScreenState())
    init {
        viewModelScope.launch {
            val movies = state.title.map { type ->
                val response = repository.getMovies(type)
                response.body()?.items ?: emptyList()
            }
            state = state.copy(
                types = movies
            )
        }
    }

}

data class ScreenState(
    val types: List<List<Movie>> = emptyList(),
    val title: List<String> = listOf("TV_SHOW","FILM","TV_SERIES","MINI_SERIES")
)