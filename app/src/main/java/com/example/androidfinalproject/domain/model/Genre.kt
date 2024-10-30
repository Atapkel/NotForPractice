package com.example.androidfinalproject.domain.model


data class Genre(
    val movies: List<Movie>,
    val genreTitle: String
)

object GenreData {
    val generes = listOf(
        Genre(movies = MovieData.movies,"Title1"),
        Genre(movies = MovieData.movies,"Title2"),
        Genre(movies = MovieData.movies,"Title3"),
        Genre(movies = MovieData.movies,"Title4"),
        Genre(movies = MovieData.movies,"Title5"),
        Genre(movies = MovieData.movies,"Title6"),
        Genre(movies = MovieData.movies,"Title7"),
    )
}