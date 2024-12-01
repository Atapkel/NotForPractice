package com.example.androidfinalproject.presentation.profile.event

import com.example.androidfinalproject.domain.dto.MovieDTO

data class MovieState(
    val movies: List<MovieDTO> = emptyList(),
    val isAddingMovie: Boolean = false,
    val collectionName: String = "",
    val posterUrl: String = "",
    val ratingKinopoisk: String = "",
    val nameRu: String = "",
    val nameEn: String? = "",
    val genres: String? = "",
    var year: Int = 0,
    val filmLength: Int = 0,
    val ratingAgeLimits: String = "",
    val countries: String? = "",
    val shortDescription: String = "",
    val description: String = "",

    )