package com.example.androidfinalproject.presentation.movie_detail

import Movie

sealed interface MovieDetailState {
    data object Initial : MovieDetailState
    data object Loading : MovieDetailState
    data class Success(val movies: Movie) : MovieDetailState
    data class Error(val message: String) : MovieDetailState
}