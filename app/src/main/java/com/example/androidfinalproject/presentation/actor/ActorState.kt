package com.example.androidfinalproject.presentation.actor

import Movie
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.domain.model.Film

sealed interface ActorState {
    data object Initial : ActorState
    data object Loading : ActorState
    data class Success(val actor: Actor, val filmCount: Int = 0, val movies: List<Movie>) :
        ActorState

    data class Error(val message: String) : ActorState
}