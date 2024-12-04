package com.example.androidfinalproject.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.util.RetrofitInstance
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FilmFiltersViewModel : ViewModel() {

    private val _state = MutableStateFlow<FilmFiltersState>(FilmFiltersState.Loading)
    val state: StateFlow<FilmFiltersState> get() = _state

    init {
        fetchFilmFilters()
    }
    private val _selectedType = MutableStateFlow("Все")
    val selectedType: StateFlow<String> = _selectedType

    private val _selectedSort = MutableStateFlow("Дата")
    val selectedSort: StateFlow<String> = _selectedSort

    private val _selectedSee = MutableStateFlow(false)
    val selectedSee: StateFlow<Boolean> = _selectedSee

    private val _selectedCountry = MutableStateFlow("")
    val selectedCountry: StateFlow<String> = _selectedCountry

    private val _selectedCountryId = MutableStateFlow(0)
    val selectedCountryId: StateFlow<Int> = _selectedCountryId

    private val _selectedGenre = MutableStateFlow("")
    val selectedGenre: StateFlow<String> = _selectedGenre

    private val _selectedGenreId = MutableStateFlow(0)
    val selectedGenreId: StateFlow<Int> = _selectedGenreId

    private val _fromYear = MutableStateFlow(0)
    val fromYear: StateFlow<Int> = _fromYear

    private val _toYear = MutableStateFlow(0)
    val toYear: StateFlow<Int> = _toYear

    private val _startRating = MutableStateFlow(0.0)
    val startRating: StateFlow<Double> = _startRating

    private val _endRating = MutableStateFlow(10.0)
    val endRating: StateFlow<Double> = _endRating

    fun setStartRating(startRating: Double){
        _startRating.value = startRating
    }
    fun setEndRating(endRating: Double){
        _endRating.value = endRating
    }

    fun setFromYear(year: Int) {
        _fromYear.value = year
    }

    fun setToYear(year: Int) {
        _toYear.value = year
    }

    fun setSelectedSee(see: Boolean){
        _selectedSee.value = see
    }

    fun setSelectedType(type: String) {
        _selectedType.value = type
    }

    fun setSelectedSort(sort: String) {
        _selectedSort.value = sort
    }

    fun setSelectedCountry(country: String) {
        _selectedCountry.value = country
    }

    fun setSelectedCountryId(countryId: Int){
        _selectedCountryId.value = countryId
    }

    fun setSelectedGenre(genre: String) {
        _selectedGenre.value = genre
    }

    fun setSelectedGenreId(genreId: Int){
        _selectedGenreId.value = genreId
    }
    private fun fetchFilmFilters() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getFilmFilters()
                if (response.isSuccessful) {
                    response.body()?.let { filters ->
                        _state.value = FilmFiltersState.Success(filters)
                    } ?: run {
                        _state.value = FilmFiltersState.Error("Empty response")
                    }
                } else {
                    _state.value = FilmFiltersState.Error("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = FilmFiltersState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class FilmFiltersState {
    object Loading : FilmFiltersState()
    data class Success(val filters: FilmFiltersResponse) : FilmFiltersState()
    data class Error(val message: String) : FilmFiltersState()
}
