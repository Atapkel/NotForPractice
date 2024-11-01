package com.example.androidfinalproject.presentation.genre

import Movie

sealed interface GenreState {
    data object Initial : GenreState
    data object Loading : GenreState
    data class Success(val data: List<Movie>) : GenreState
    data class Error(val message: String) : GenreState
}