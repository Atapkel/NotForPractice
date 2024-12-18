package com.example.androidfinalproject.util

import android.content.Context
import androidx.room.Room
import com.example.androidfinalproject.data.local.MovieDatabase

object RoomInstance {
    private var instance: MovieDatabase? = null

    fun getDatabase(context: Context): MovieDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "contacts.db"
            ).allowMainThreadQueries().build().also { instance = it }
        }
    }
}
