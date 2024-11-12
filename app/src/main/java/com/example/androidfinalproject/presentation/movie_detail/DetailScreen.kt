package com.example.androidfinalproject.presentation.movie_detail

import Movie
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.presentation.movie_detail.component.DetailScreenOfMovie

@Composable
fun DetailScreen(id: Int) {
    val viewModel: DetailScreenViewModel = remember { DetailScreenViewModel(id) }

    when (val state = viewModel.stateOfMovieDetail) {
        is MovieDetailState.Initial -> {
        }

        is MovieDetailState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color(0xFF3D3BFF))
            }
        }

        is MovieDetailState.Success -> {
            when (val stateOfActors = viewModel.stateOfActorsByFilm) {
                is ActorsByFilmIdState.Initial -> {}
                is ActorsByFilmIdState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = Color(0xFF3D3BFF))
                    }
                }
                is ActorsByFilmIdState.Success ->{
                    val actors: List<ActorByFilm> = stateOfActors.actors;
                    val movie: Movie = state.movies;
                    DetailScreenOfMovie(actors = actors, movie = movie)
                }
                is ActorsByFilmIdState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.eye_icon),
                            contentDescription = "Error",
                            modifier = Modifier.size(25.dp)
                        )
                        Text(text = stateOfActors.message, color = Color.Red)
                    }
                }
            }



        }

        is MovieDetailState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.eye_icon),
                    contentDescription = "Error",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = state.message, color = Color.Red)
            }
        }
    }


}