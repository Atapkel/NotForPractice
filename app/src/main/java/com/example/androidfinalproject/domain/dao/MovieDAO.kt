package com.example.androidfinalproject.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.androidfinalproject.domain.dto.MovieDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {
    @Upsert
    suspend fun upsertMovie(movieDTO: MovieDTO)

    @Query("SELECT * FROM MovieDTO")
    fun getMovies(): Flow<List<MovieDTO>>

    @Query("SELECT * FROM MovieDTO WHERE collectionName=:collectionName")
    fun getMoviesByCollectionName(collectionName: String): Flow<List<MovieDTO>>

    @Query("DELETE FROM MOVIEDTO where collectionName=:collectionName")
    fun delete(collectionName: String)

}