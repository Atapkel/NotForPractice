package com.example.androidfinalproject.presentation.genre

import Movie
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import kotlinx.coroutines.launch


class GenreModelView(private val type: String) : ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf(State())

    init {
        viewModelScope.launch {
            val response = repository.getMovies(type)
            if (response.isSuccessful) {
                state = state.copy(
                    types = response.body()?.items ?: emptyList()
                )
            } else {
                state = state.copy(types = emptyList())
            }
        }
    }
}

data class State(
    val types: List<Movie> = emptyList()
)
