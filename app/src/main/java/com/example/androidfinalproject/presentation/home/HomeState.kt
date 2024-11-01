package com.example.androidfinalproject.presentation.home

import Movie

sealed interface HomeState {
    data object Initial : HomeState
    data object Loading : HomeState
    data class Success(val data: List<List<Movie>>) : HomeState
    data class Error(val message: String) : HomeState
}
