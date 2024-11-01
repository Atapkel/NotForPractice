package com.example.androidfinalproject.presentation.genre

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import kotlinx.coroutines.launch


class GenreModelView(type: String) : ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf<GenreState>(GenreState.Initial)
        private set

    init {
        fetchMovies(type)
    }

    private fun fetchMovies(type: String) {
        state = GenreState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getMovies(type)
                if (response.isSuccessful) {
                    state = GenreState.Success(response.body()?.items ?: emptyList())
                } else {
                    state = GenreState.Error("Failed to load movies: ${response.message()}")
                }
            } catch (e: Exception) {
                state = GenreState.Error("An error occurred: ${e.localizedMessage}")
            }
        }
    }
}