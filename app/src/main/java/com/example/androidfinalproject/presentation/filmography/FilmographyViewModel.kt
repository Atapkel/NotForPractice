package com.sdu.skillcinema.presentation.staff_filmography

import Movie
import ProfessionKey
import androidx.core.view.OneShotPreDrawListener.add
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.presentation.filmography.FilmographyState
import getProfessionKeyFromString
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FilmographyViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(FilmographyState())
    val state: StateFlow<FilmographyState> = _state

    private val repository = Repository()

    init {
        val id: Int? = savedStateHandle.get<String>("staffId")?.toInt()

        if (id != null) {
            getStaffFilmographyById(id)
        }
    }

    fun changeFilmographyType(professionKey: ProfessionKey) {
        _state.value = _state.value.copy(professionKey = professionKey)
    }

    private fun getStaffFilmographyById(id: Int) {
        viewModelScope.launch {
            _state.value = FilmographyState(isLoading = true)
            var initialKey: ProfessionKey = ProfessionKey.ACTOR

            try {
                val response = repository.getActorDetailById(id)
                val staff = response.body() ?: Actor()
                val staffFilms = staff.films
                val moviesMap: MutableMap<ProfessionKey, MutableList<Movie>> = mutableMapOf()

                staffFilms.forEachIndexed { _, film ->
                    val response = repository.getFilmDetailById(film.filmId)
                    val movie = response.body() ?: Movie()
                    val professionKey = getProfessionKeyFromString(film.professionKey)
                    val moviesForKey = moviesMap.getOrPut(professionKey) { mutableListOf() }

                    moviesForKey.add(movie)
                    initialKey = professionKey
                }


                _state.value = FilmographyState(
                    movies = moviesMap,
                    staffName = staff.nameRu,
                    professionKey = initialKey
                )
            } catch (e: Exception) {
                _state.value = FilmographyState(error = "Unexpected error occured")
            }
        }
    }

}


}
