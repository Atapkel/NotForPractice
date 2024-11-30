package com.example.androidfinalproject.domain.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val collectionName: String = "",
    val posterUrl: String = "",
    val ratingKinopoisk: String = "",
    val nameRu: String = "",
    val nameEn: String? = "",
    val genres: String? = "",
    val year: Int = 0,
    val filmLength: Int = 0,
    val ratingAgeLimits: String = "",
    val countries: String? = "",
    val shortDescription: String = "",
    val description: String = "",

    )


