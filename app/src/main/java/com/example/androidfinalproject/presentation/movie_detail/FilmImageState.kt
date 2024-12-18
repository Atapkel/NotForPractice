package com.example.androidfinalproject.presentation.movie_detail


import com.example.androidfinalproject.domain.model.ImageOfFilm

sealed interface FilmImagesState {
    data object Initial : FilmImagesState
    data object Loading : FilmImagesState
    data class Success(val images: List<ImageOfFilm>) : FilmImagesState
    data class Error(val message: String) : FilmImagesState
}
