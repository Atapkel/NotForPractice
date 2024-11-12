package com.example.androidfinalproject.presentation.movie_detail

import Movie
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.presentation.genre.GenreState
import kotlinx.coroutines.launch

class DetailScreenViewModel(id: Int):ViewModel() {

    private val repository = Repository()
    var state by mutableStateOf<MovieDetailState>(MovieDetailState.Initial)
        private set

    init {
        fetchMovieDetail(id)
    }

    private fun fetchMovieDetail(id: Int) {
        state = MovieDetailState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getFilmDetailById(id)
                if (response.isSuccessful) {
                    state = MovieDetailState.Success(response.body() ?: Movie())
                } else {
                    state = MovieDetailState.Error("Failed to load movies: ${response.message()}")
                }
            } catch (e: Exception) {
                state = MovieDetailState.Error("An error occurred: ${e.localizedMessage}")
            }
        }
    }

}