package com.example.androidfinalproject.presentation.search

import FilteredMoviesState
import FilteredMoviesViewModel
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.search.components.MovieCard1
import com.example.androidfinalproject.presentation.search.components.SearchBar


@Composable
fun SearchScreen(navController: NavHostController, filtersViewModel: FilmFiltersViewModel) {
    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.state.collectAsState()


    val newFilteredMoviesViewModel: FilteredMoviesViewModel = viewModel()
    val filteredState by newFilteredMoviesViewModel.movieState.collectAsState()

    var keyword by remember { mutableStateOf("") }

    val typeBy = mapOf(
        "Фильмы" to "FILM",
        "Сериалы" to "TV_SERIES",
        "Все" to "ALL"
    )
    val sortBy = mapOf(
        "Рейтинг" to "RATING",
        "Популярность" to "NUM_VOTE",
        "Дата" to "YEAR"
    )
    val selectedType = filtersViewModel.selectedType.collectAsState().value
    val selectedSort = filtersViewModel.selectedSort.collectAsState().value
    val selectedCountry = filtersViewModel.selectedCountryId.collectAsState().value
    val selectedGenre = filtersViewModel.selectedGenreId.collectAsState().value
    val toYear = filtersViewModel.toYear.collectAsState().value
    val fromYear = filtersViewModel.fromYear.collectAsState().value
    val startRating = filtersViewModel.startRating.collectAsState().value
    val endRating = filtersViewModel.endRating.collectAsState().value
    val type = typeBy[selectedType] ?: "ALL"
    val sort = sortBy[selectedSort] ?: "YEAR"
    Log.d(
        "filtered_search",
        "$selectedCountry, $selectedGenre, $toYear, $fromYear, $startRating, $endRating, $type, $sort "
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp)
    ) {
        SearchBar(
            navController = navController,
            query = keyword,
            onQueryChange = { newValue ->
                keyword = newValue
                if (keyword.isNotBlank() && selectedCountry != 0 && selectedGenre != 0 && toYear != 0 && fromYear != 0
                    && startRating != 0.0 && endRating != 10.0 && sort != "YEAR" && type != "ALL"
                ) {
                    viewModel.searchFilm(newValue)
                } else {
                    newFilteredMoviesViewModel.getFilteredMovies(
                        country = selectedCountry,
                        genre = selectedGenre,
                        order = sort,
                        type = type,
                        ratingFrom = startRating,
                        ratingTo = endRating,
                        yearFrom = fromYear,
                        yearTo = toYear,
                        keyword = keyword
                    )

                }
            }
        )
        when (filteredState) {
            is FilteredMoviesState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val errorMessage = (state as SearchState.Error).message
                    Text(
                        text = if (errorMessage == "No films found") {
                            "К сожалению, по вашему запросу ничего не найдено"
                        } else {
                            "failed to load data"
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF272727),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }

            FilteredMoviesState.Initial -> {

            }

            FilteredMoviesState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color(0xFF3D3BFF))
                }
            }

            is FilteredMoviesState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    items((filteredState as FilteredMoviesState.Success).movies) { film ->
                        val item = FilmItem(
                            film.nameRu, film.kinopoiskId,
                            film.ratingKinopoisk.toString(),
                            film.posterUrl, film.year.toString(), film.genres
                        )
                        MovieCard1(filmS = item, navController = navController)
                    }
                }
            }

            else -> {}
        }
        when (state) {
            is SearchState.Initial -> {
            }

            is SearchState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color(0xFF3D3BFF))
                }
            }

            is SearchState.Success -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ) {
                    items((state as SearchState.Success).films) { film ->
                        val item = FilmItem(
                            film.nameRu.toString(), film.filmId, film.rating,
                            film.posterUrl.toString(), film.year, film.genres
                        )
                        MovieCard1(filmS = item, navController = navController)
                    }
                }
            }

            is SearchState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val errorMessage = (state as SearchState.Error).message
                    Text(
                        text = if (errorMessage == "No films found") {
                            "К сожалению, по вашему запросу ничего не найдено"
                        } else {
                            "Failed to load data"
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_medium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF272727),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}
