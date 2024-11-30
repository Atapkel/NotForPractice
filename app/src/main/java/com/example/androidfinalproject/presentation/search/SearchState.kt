package com.example.androidfinalproject.presentation.search

import com.example.androidfinalproject.domain.model.FilmSearch

sealed interface SearchState {
    data object Initial : SearchState
    data object Loading : SearchState
    data class Success(val films : List<FilmSearch>) : SearchState
    data class Error(val message: String) : SearchState
}