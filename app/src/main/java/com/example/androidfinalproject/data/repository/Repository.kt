package com.example.androidfinalproject.data.repository

import MovieResponse
import com.example.androidfinalproject.util.RetrofitInstance
import retrofit2.Response

class Repository {
    suspend fun getMovies(type: String): Response<MovieResponse>{
        return RetrofitInstance.api.getMovies(type)
    }
}