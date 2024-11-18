package com.example.androidfinalproject.presentation.filmography

import Movie
import ProfessionKey


data class FilmographyState(
    var isLoading: Boolean = false,
    var movies: Map<ProfessionKey, List<Movie>> = emptyMap(),
    var staffName: String = "",
    var error: String = "",
    var professionKey: ProfessionKey = ProfessionKey.ACTOR
)