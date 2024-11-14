package com.example.androidfinalproject.domain.model

data class FilmImagesResponse(
    val total: Int,
    val totalPages: Int?,
    val items: List<FilmImage>
)

data class FilmImage(
    val imageUrl: String,
    val previewUrl: String
)
