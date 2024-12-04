package com.example.androidfinalproject.presentation.search

import com.example.androidfinalproject.domain.model.Country
import com.example.androidfinalproject.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class FilmFiltersResponse(
    val genres: List<Genre>,
    val countries: List<Country>
)