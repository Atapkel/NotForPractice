package com.example.androidfinalproject.domain.model
data class SimilarFilmsResponse(
    val total: Int,
    val items: List<SimilarFilm>
)

data class SimilarFilm(
    val filmId: Int,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val relationType: String
)
