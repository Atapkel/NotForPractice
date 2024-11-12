package com.example.androidfinalproject.domain.model

data class Actor(
    val age: Int = 0,
    val birthday: String = "",
    val birthplace: String = "",
    val death: String = "",
    val deathplace: String = "",
    val facts: List<String> = emptyList(),
    val films: List<Film> = emptyList(),
    val growth: String = "",
    val hasAwards: Int = 0,
    val nameEn: String = "",
    val nameRu: String = "",
    val personId: Int = 0,
    val posterUrl: String = "",
    val profession: String = "",
    val sex: String = "",
    val spouses: List<Spouse> = emptyList(),
    val webUrl: String = ""
)

data class Spouse(
    val children: Int,
    val divorced: Boolean,
    val divorcedReason: String,
    val name: String,
    val personId: Int,
    val relation: String,
    val sex: String,
    val webUrl: String
)

data class Film(
    val description: String,
    val filmId: Int,
    val general: Boolean,
    val nameEn: String,
    val nameRu: String,
    val professionKey: String,
    val rating: String
)