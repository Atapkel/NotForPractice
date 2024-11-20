package com.example.androidfinalproject.presentation.filmography

import ConvertProfessionKeyToDescription
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.filmography.components.FilmographyMovie
import com.example.androidfinalproject.presentation.filmography.components.FilmographyTypeClickable
import com.sdu.skillcinema.presentation.staff_filmography.FilmographyViewModel

@Composable
fun StaffFilmographyScreen(
    staffFilmographyViewModel: FilmographyViewModel = viewModel(),
    navController: NavController
) {

    val state by staffFilmographyViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .background(Color.Transparent)
            ) {
                Icon (
                    painter = painterResource(id = R.drawable.caret_left),
                    contentDescription = "Back icon",
                )
            }

            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Фильмография",
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp,
                    lineHeight = 13.2.sp,
                    color = Color(0xFF272727)
                )
            )
        }
        if ( state.isLoading ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if ( state.error.isNotBlank() ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Text(
                    text = state.error,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        } else {

            Text(
                text = state.staffName,
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    lineHeight = 19.8.sp
                ),
                modifier = Modifier
                    .padding(start = 26.dp)
            )

            LazyRow(
                modifier = Modifier
                    .padding(top = 20.dp, start = 26.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.movies.toList()) { (key, value) ->
                    FilmographyTypeClickable(
                        title = ConvertProfessionKeyToDescription(key),
                        movieCount = value.size,
                        onClick = { staffFilmographyViewModel.changeFilmographyType(key) },
                        isSelected = key == state.professionKey
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(top = 24.dp)
            ) {
                items(state.movies.get(state.professionKey)!!) { movie ->
                    FilmographyMovie(movie = movie, path = { route -> navController.navigate(route) })
                }
            }

        }
    }

}