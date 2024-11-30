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

    @Query("DELETE  FROM MovieDTO where id>0")
    fun delete()

    @Query("SELECT * FROM MovieDTO")
    fun getMovies(): Flow<List<MovieDTO>>
}