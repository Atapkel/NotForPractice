package com.example.androidfinalproject.data.repository

import Movie
import MovieResponse
import android.util.Log
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.domain.model.FilmSearch
import com.example.androidfinalproject.domain.model.ImagesOfFilm
import com.example.androidfinalproject.domain.model.SimilarFilmsResponse
import com.example.androidfinalproject.util.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovies(type: String): Response<MovieResponse> {
        return RetrofitInstance.api.getMovies(type)
    }

    suspend fun getFilmDetailById(id: Int): Response<Movie> {
        return RetrofitInstance.api.getFilmDetailById(id)
    }

    suspend fun getFilmActors(filmId: Int): Response<List<ActorByFilm>> {
        return RetrofitInstance.api.getFilmActors(filmId)
    }

    suspend fun getActorDetailById(id: Int): Response<Actor> {
        return RetrofitInstance.api.getActorDetailById(id)
    }

    suspend fun getSimilarFilmsById(id: Int): Response<SimilarFilmsResponse> {
        return RetrofitInstance.api.getSimilarFilmsById(id)
    }

    suspend fun getFilmImages(id: Int): Response<ImagesOfFilm> {
        return RetrofitInstance.api.getFilmImagesById(id)
    }

    suspend fun searchFilms(keyword: String): Response<FilmSearch> {
        Log.d("repository_logic", "starting API call with keyword: $keyword")
        val response = RetrofitInstance.api.searchFilms(keyword)
        Log.d("repository_logic", "received response with code: ${response.code()}")
        return RetrofitInstance.api.searchFilms(keyword)
    }

    suspend fun getFilteredMovies(
        country: Int,
        genre: Int,
        order: String,
        type: String,
        ratingFrom: Double,
        ratingTo: Double,
        yearFrom: Int,
        yearTo: Int,
        keyword: String,
        page:Int = 1
    ): Response<MovieResponse> {
        return RetrofitInstance.api.getFilteredMovies(
            country,
            genre,
            order,
            type,
            ratingFrom,
            ratingTo,
            yearFrom,
            yearTo,
            keyword,
            page
        )
    }
}