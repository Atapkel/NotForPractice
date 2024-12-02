package com.example.androidfinalproject.presentation.profile.event

import com.example.androidfinalproject.domain.dto.MovieDTO


sealed interface MovieEvent {
    data class SaveMovie(val movieDTO: MovieDTO) : MovieEvent
    object ShowMovie : MovieEvent
    object HideMovie : MovieEvent
    data class DeleteMovie(val collectionName: String) : MovieEvent
    data class FindByCollectionName(val collectionName: String): MovieEvent

}
