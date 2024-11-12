package com.example.androidfinalproject.presentation.movie_detail

import com.example.androidfinalproject.domain.model.ActorByFilm

sealed interface ActorsByFilmIdState {
    data object Initial : ActorsByFilmIdState
    data object Loading : ActorsByFilmIdState
    data class Success(val actors: List<ActorByFilm>) : ActorsByFilmIdState
    data class Error(val message: String) : ActorsByFilmIdState
}