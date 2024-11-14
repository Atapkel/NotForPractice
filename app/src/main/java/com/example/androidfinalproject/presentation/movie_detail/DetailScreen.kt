package com.example.androidfinalproject.presentation.movie_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.androidfinalproject.domain.model.FilmImage
import com.example.androidfinalproject.presentation.movie_detail.component.DetailScreenOfMovie

@Composable
fun DetailScreen(id: Int, navController: NavHostController) {
    val viewModel: DetailScreenViewModel = remember { DetailScreenViewModel(id) }
    when (val state = viewModel.stateOfMovieDetail) {
        is MovieDetailState.Initial -> {}
        is MovieDetailState.Error -> {}
        is MovieDetailState.Loading -> {LoadingState()}
        is MovieDetailState.Success -> {
            when (val stateOfActors = viewModel.stateOfActorsByFilm) {
                is ActorsByFilmIdState.Initial -> {}
                is ActorsByFilmIdState.Error -> {}
                is ActorsByFilmIdState.Loading -> {LoadingState()}
                is ActorsByFilmIdState.Success -> {
                    when(val stateImages = viewModel.stateOfImagesByFilm){
                        is FilmImagesState.Initial->{}
                        is FilmImagesState.Loading->{}
                        is FilmImagesState.Error->{}
                        is FilmImagesState.Success->{
                            when(val stateSimilarFilms = viewModel.stateOfSimilarFilm){
                                is SimilarFilmsState.Initial->{}
                                is SimilarFilmsState.Loading->{}
                                is SimilarFilmsState.Error->{}
                                is SimilarFilmsState.Success->{
                                    val images: List<FilmImage> = stateImages.images
                                    val actors = stateOfActors.actors
                                    val movie = state.movies
                                    val similarFilms = stateSimilarFilms.similarFilms
                                    DetailScreenOfMovie(stuffs = actors, movie = movie, images = images, similar = similarFilms, navController = navController)
                                }
                            }

                        }
                    }

                }
            }
        }
    }
}


@Composable
fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color(0xFF3D3BFF))
    }
}