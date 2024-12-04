package com.example.androidfinalproject.data.remote

import Movie
import MovieResponse
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.domain.model.FilmSearch
import com.example.androidfinalproject.domain.model.ImagesOfFilm
import com.example.androidfinalproject.domain.model.SimilarFilmsResponse
import com.example.androidfinalproject.presentation.search.FilmFiltersResponse
import com.example.androidfinalproject.util.Constants.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/v2.2/films")
    suspend fun getMovies(
        @Query("type") type: String = "FILM",
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<MovieResponse>

    @GET("/api/v1/staff/{id}")
    suspend fun getActorDetailById(
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<Actor>

    @GET("/api/v2.2/films/{id}")
    suspend fun getFilmDetailById(
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<Movie>

    @GET("/api/v1/staff")
    suspend fun getFilmActors(
        @Query("filmId") filmId: Int,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<List<ActorByFilm>>

    @GET("/api/v2.2/films/{id}/images")
    suspend fun getFilmImagesById(
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<ImagesOfFilm>

    @GET("/api/v2.2/films/{id}/similars")
    suspend fun getSimilarFilmsById(
        @Path("id") id: Int,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<SimilarFilmsResponse>

    @GET("/api/v2.1/films/search-by-keyword")
    suspend fun searchFilms(
        @Query("keyword") keyword: String,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<FilmSearch>
    @GET("/api/v2.2/films/filters")
    suspend fun getFilmFilters(
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<FilmFiltersResponse>
    @GET("/api/v2.2/films")
    suspend fun getFilteredMovies(
        @Query("countries") country: Int,
        @Query("genres") genre: Int,
        @Query("order") order: String,
        @Query("type") type: String,
        @Query("ratingFrom") ratingFrom: Double,
        @Query("ratingTo") ratingTo: Double,
        @Query("yearFrom") yearFrom: Int,
        @Query("yearTo") yearTo: Int,
        @Query("keyword") keyword: String,
        @Query("page") page: Int = 1,
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<MovieResponse>

}