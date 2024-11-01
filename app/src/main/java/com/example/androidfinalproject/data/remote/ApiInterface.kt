package com.example.androidfinalproject.data.remote

import MovieResponse
import com.example.androidfinalproject.util.Constants
import com.example.androidfinalproject.util.Constants.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api/v2.2/films")
    suspend fun getMovies(
        @Query("type") type: String = "FILM",
        @Header("X-API-KEY") apiKey: String = TOKEN
    ): Response<MovieResponse>
}