package com.example.androidfinalproject.domain.model

data class FilmSearch(
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int = 0,
    val films: List<FilmS>
)

data class FilmS(
    val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val type: String?,
    val year: String?,
    val description: String?,
    val filmLength: String?,
    val countries: List<CountryS>,
    val genres: List<GenreS>,
    val rating: String?,
    val ratingVoteCount: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)

data class CountryS(val country: String)
data class GenreS(val genre: String)

