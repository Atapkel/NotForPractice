package com.example.androidfinalproject.presentation.movie_detail

import com.example.androidfinalproject.domain.model.ImageOfFilm

sealed interface ImageState {
    data object Initial : ImageState
    data object Loading : ImageState
    data class Success(val images: List<ImageOfFilm>) : ImageState
    data class Error(val message: String) : ImageState
}