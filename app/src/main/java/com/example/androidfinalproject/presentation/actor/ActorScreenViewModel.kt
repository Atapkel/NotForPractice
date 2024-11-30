package com.example.androidfinalproject.presentation.actor

import Movie
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.domain.model.Film
import com.example.androidfinalproject.presentation.movie_detail.DetailScreenViewModel
import kotlinx.coroutines.launch

class ActorScreenViewModel(id: Int): ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf<ActorState>(ActorState.Initial)
        private set

    init {
        fetchActor(id)
    }

    private fun fetchActor(id: Int) {
        state = ActorState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getActorDetailById(id)
                if (response.isSuccessful) {
                    val actor = response.body() ?: Actor()
                    val generalFilmIds = actor.films.filter { it.general }.map { it.filmId }
                    val filmCount = actor.films.size
                        val movies = fetchMovies(generalFilmIds)
                    state = ActorState.Success(
                        actor = actor,
                        filmCount = filmCount,
                        movies = movies
                    )
                } else {
                    state = ActorState.Error("Failed to load actor: ${response.message()}")
                }
            } catch (e: Exception) {
                state = ActorState.Error("An error occurred when load actor: ${e.localizedMessage}")
            }
        }
    }
    private suspend fun fetchMovies(filmIds: List<Int>): List<Movie> {
        val movies = mutableListOf<Movie>()
        for (filmId in filmIds) {
            val response = repository.getFilmDetailById(filmId)
            val movie = response.body() ?: Movie()
            movies.add(movie)
        }
        return movies
    }
}