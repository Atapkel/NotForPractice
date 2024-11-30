package com.example.androidfinalproject.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.domain.dao.MovieDAO
import com.example.androidfinalproject.domain.dto.MovieDTO
import com.example.androidfinalproject.presentation.profile.event.MovieEvent
import com.example.androidfinalproject.presentation.profile.event.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileScreenViewModel(
    private val dao: MovieDAO
) : ViewModel() {
    private val _state = MutableStateFlow(MovieState())
    private val _movies = dao.getMovies()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val state = combine(_state, _movies) { state, movies ->
        state.copy(movies = movies)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MovieState())

    fun onEvent(event: MovieEvent) {
        when (event) {
            MovieEvent.HideMovie -> {
                _state.update { it.copy(isAddingMovie = false) }
            }



            MovieEvent.ShowMovie -> {
                _state.update {
                    it.copy(isAddingMovie = true)
                }
            }

            is MovieEvent.DeleteMovie -> {
                viewModelScope.launch { dao.delete() }
            }

            is MovieEvent.SaveMovie -> {
                viewModelScope.launch {
                    try {
                        dao.upsertMovie(event.movieDTO)
                        println("Movie saved: ${event.movieDTO.nameRu}")
                    } catch (e: Exception) {
                        println("Error saving movie: ${e.message}")
                    }
                }
            }
        }
    }
}