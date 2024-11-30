package com.example.androidfinalproject.presentation.search

import com.example.androidfinalproject.domain.model.FilmS

sealed interface SearchState {
    data object Initial : SearchState
    data object Loading : SearchState
    data class Success(val films: List<FilmS>) : SearchState
    data class Error(val message: String) : SearchState
}