package com.example.androidfinalproject.presentation.movie_detail

import com.example.androidfinalproject.domain.model.SimilarFilm

sealed interface SimilarFilmsState {
    data object Initial : SimilarFilmsState
    data object Loading : SimilarFilmsState
    data class Success(val similarFilms: List<SimilarFilm>) : SimilarFilmsState
    data class Error(val message: String) : SimilarFilmsState
}
