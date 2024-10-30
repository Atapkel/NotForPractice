package com.example.androidfinalproject.domain.model

import com.example.androidfinalproject.R


data class Movie(
    val title: String,
    val category: String,
    val rate: Double,
    val isSeen: Boolean = false,
    val imageId: Int,
)

object MovieData {
    val movies = listOf(
        Movie("Близкие", "драма", 7.8, isSeen = false, imageId = R.drawable.onboard1),
        Movie("Легенда", "приключения", 8.5, isSeen = true, imageId = R.drawable.onboard1),
        Movie("Тайна", "ужасы", 6.5, isSeen = false, imageId = R.drawable.onboard1),
        Movie("Супергерой", "экшн", 9.0, isSeen = true, imageId = R.drawable.onboard1),
        Movie("Комедия", "комедия", 7.0, isSeen = true, imageId = R.drawable.onboard1),
        Movie("Мелодрама", "романтика", 6.9, isSeen = false, imageId = R.drawable.onboard1)
    )
}