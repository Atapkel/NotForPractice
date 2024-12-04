package com.example.androidfinalproject.presentation.search

import com.example.androidfinalproject.domain.model.Genre

data class FilmItem(
    val nameRu: String,
    val filmId: Int,
    val rating: String?,
    val posterUrl: String,
    val year: String?,
    val genres: List<Genre>
)
