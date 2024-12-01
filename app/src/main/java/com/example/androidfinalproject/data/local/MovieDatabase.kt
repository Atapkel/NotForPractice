package com.example.androidfinalproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidfinalproject.domain.dao.MovieDAO
import com.example.androidfinalproject.domain.dto.MovieDTO

@Database(
    entities = [MovieDTO::class],
    version = 1
)
abstract class MovieDatabase: RoomDatabase() {
    abstract val dao: MovieDAO
}