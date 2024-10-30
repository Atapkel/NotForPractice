package com.example.androidfinalproject.presentation.onBoarding


import com.example.androidfinalproject.R

data class Page(
    val title: String,
    val description: String,
    val imageId: Int
)


val pages = listOf(
    Page(
        "Узнавай\nо премьерах",
        "First Content",
        R.drawable.onboard1
    ),
    Page(
        "Создавай\nколлекции",
        "Second content",
        R.drawable.onboard2
    ),
    Page(
        "Делись\nс друзьями",
        "Third Content",
        R.drawable.onboard3
    )
)