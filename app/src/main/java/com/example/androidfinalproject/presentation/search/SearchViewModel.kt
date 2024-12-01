package com.example.androidfinalproject.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.domain.model.FilmSearch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = Repository()

    private val _state = MutableStateFlow<SearchState>(SearchState.Initial)
    val state: StateFlow<SearchState> = _state

    fun searchFilm(keyword: String) {
        if (keyword.isBlank()) {
            _state.value = SearchState.Initial
            return
        }
        _state.value = SearchState.Loading
        viewModelScope.launch {
            try {
                val response = repository.searchFilms(keyword)
                if (response.isSuccessful) {
                    val searchResponse = response.body() ?: FilmSearch()
                    val films = searchResponse.films
                    _state.value = if (films.isNotEmpty()) {
                        SearchState.Success(films)
                    } else {
                        SearchState.Error(message = "No films found")
                    }
                } else {
                    _state.value = SearchState.Error("Failed to load data: ${response.message()}")
                }
            } catch (e: Exception) {
                _state.value = SearchState.Error("Exception: ${e.message}")
            }
        }
    }
}