package com.example.androidfinalproject.presentation.actor

import com.example.androidfinalproject.domain.model.Actor

sealed interface ActorState {
    data object Initial : ActorState
    data object Loading : ActorState
    data class Success(val actor: Actor) : ActorState
    data class Error(val message: String) : ActorState
}