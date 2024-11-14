package com.example.androidfinalproject.presentation.movie_detail

import Movie
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import kotlinx.coroutines.launch

class DetailScreenViewModel(id: Int):ViewModel() {

    private val repository = Repository()
    var stateOfMovieDetail by mutableStateOf<MovieDetailState>(MovieDetailState.Initial)
        private set
    var stateOfActorsByFilm by mutableStateOf<ActorsByFilmIdState>(ActorsByFilmIdState.Initial)
        private set
    var stateOfImagesByFilm by mutableStateOf<FilmImagesState>(FilmImagesState.Initial)
        private set
    var stateOfSimilarFilm by mutableStateOf<SimilarFilmsState>(SimilarFilmsState.Initial)
        private set
    init {
        fetchMovieImages(id)
        fetchMovieDetail(id)
        fetchMovieActors(id)
        fetchMovieSimilars(id)
    }

    private fun fetchMovieSimilars(id: Int) {
        stateOfSimilarFilm = SimilarFilmsState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getSimilarFilmsById(id)
                if (response.isSuccessful) {
                    stateOfSimilarFilm = SimilarFilmsState.Success(response.body()?.items?: emptyList())
                } else {
                    stateOfSimilarFilm = SimilarFilmsState.Error("Failed to load movies: ${response.message()}")
                }
            } catch (e: Exception) {
                stateOfSimilarFilm = SimilarFilmsState.Error("An error occurred when load movies: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchMovieDetail(id: Int) {
        stateOfMovieDetail = MovieDetailState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getFilmDetailById(id)
                if (response.isSuccessful) {
                    stateOfMovieDetail = MovieDetailState.Success(response.body()?: Movie())
                } else {
                    stateOfMovieDetail = MovieDetailState.Error("Failed to load movies: ${response.message()}")
                }
            } catch (e: Exception) {
                stateOfMovieDetail = MovieDetailState.Error("An error occurred when load movies: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchMovieActors(id: Int) {
        stateOfActorsByFilm = ActorsByFilmIdState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getFilmActors(id)
                if (response.isSuccessful) {
                    stateOfActorsByFilm = ActorsByFilmIdState.Success(response.body() ?: emptyList())
                } else {
                    stateOfActorsByFilm = ActorsByFilmIdState.Error("Failed to load actors: ${response.message()}")
                }
            } catch (e: Exception) {
                stateOfActorsByFilm = ActorsByFilmIdState.Error("An error occurred when Actors loaded: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchMovieImages(id: Int) {
        stateOfImagesByFilm = FilmImagesState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getFilmImages(id)
                if (response.isSuccessful) {
                    stateOfImagesByFilm = FilmImagesState.Success(response.body()?.items?: emptyList())
                } else {
                    stateOfImagesByFilm = FilmImagesState.Error("Failed to load actors: ${response.message()}")
                }
            } catch (e: Exception) {
                stateOfImagesByFilm = FilmImagesState.Error("An error occurred when Actors loaded: ${e.localizedMessage}")
            }
        }
    }


}