package com.example.androidfinalproject.domain.model

data class FilmSearch(
    val keyword: String = "",
    val pagesCount: Int = 0,
    val searchFilmsCountResult: Int = 0,
    val films: List<FilmS> = emptyList()
)

data class FilmS(
    val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val type: String?,
    val year: String?,
    val description: String?,
    val filmLength: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String?,
    val ratingVoteCount: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)
