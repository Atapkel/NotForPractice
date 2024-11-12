package com.example.androidfinalproject.data.repository

import Movie
import MovieResponse
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.domain.model.ImagesOfFilm
import com.example.androidfinalproject.util.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovies(type: String): Response<MovieResponse> {
        return RetrofitInstance.api.getMovies(type)
    }

    suspend fun getFilmDetailById(id: Int): Response<Movie> {
        return RetrofitInstance.api.getFilmDetailById(id)
    }

    suspend fun getFilmActors(filmId: Int): Response<List<ActorByFilm>>{
        return RetrofitInstance.api.getFilmActors(filmId)
    }

    suspend fun getActorDetailById(id: Int): Response<Actor> {
        return RetrofitInstance.api.getActorDetailById(id)
    }

    suspend fun getFilmImagesById(id: Int): Response<ImagesOfFilm> {
        return RetrofitInstance.api.getFilmImagesById(id)
    }
}