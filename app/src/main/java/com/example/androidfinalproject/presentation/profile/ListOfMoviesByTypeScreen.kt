package com.example.androidfinalproject.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.dto.MovieDTO
import com.example.androidfinalproject.presentation.genre.components.ListPage
import com.example.androidfinalproject.presentation.graph.ProfileRoutes
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.BottomNavigationItem
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes
import com.example.androidfinalproject.presentation.profile.event.MovieEvent


@Composable
fun ListOfMoviesScreen(viewModel: ProfileScreenViewModel, type: String, path: (String) -> Unit) {
    val state = viewModel.state.collectAsState().value
    val allMovies = state.movies
    val movies = mutableListOf<MovieDTO>()
    for (movie in allMovies) {
        if (movie.collectionName.equals(type)) movies.add(movie)
    }
    if (movies.isEmpty()) {
        Text(text = "movies is empty")
    } else {
        val m = movies.get(0)
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 16.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.caret_left),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            path("popBackStack")
                        }
                )

                Text(
                    text = m.collectionName,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF272727),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )


            }
            Spacer(Modifier.height(32.dp))
            ListOfMoviesByTypePage(movies, path, viewModel)
        }
    }
}


@Composable
fun ListOfMoviesByTypePage(
    movies: List<MovieDTO>,
    path: (String) -> Unit,
    viewModel: ProfileScreenViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 62.dp)
            .height(194.dp)
            .width(111.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

//        println("Movies list 1")
        items(movies) { movie ->
            MovieDTOCard(movie = movie, onClick = {
                println("navigated to ${movie.id}")
                path(ProfileRoutes.DETAIL_OF_SAVED_MOVIE + "/${movie.id}")
            })
        }
//        println("Movies list 2 ${movies.get(0).collectionName}")

        item {
            Button(modifier = Modifier
                .width(125.dp)
                .height(36.dp),
                shape = RoundedCornerShape(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D3BFF)),
                onClick = {
                    if (movies.size > 0) {
//                    println("I want delete collection with name ${movies.get(0).collectionName}")
                        viewModel.onEvent(MovieEvent.DeleteMovie(movies.get(0).collectionName))
                    }
                }) {
                Text(
                    text = "DELETE ALL",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}


@Composable
fun MovieDTOCard(
    movie: MovieDTO,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(111.dp)
            .height(194.dp)
            .clickable {
                onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .height(156.dp)
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                error = painterResource(id = R.drawable.download)
            )

            Box(
                Modifier
                    .offset(x = -6.dp, y = 6.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .width(17.dp)
                    .height(10.dp)
                    .background(Color(0xFF3D3BFF))
                    .align(Alignment.TopEnd)
            ) {
                Text(
                    text = movie.ratingKinopoisk.toString(),
                    fontSize = 6.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            if (false) {
                Icon(
                    painter = painterResource(R.drawable.eye_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .offset(x = -6.dp, y = -6.dp)
                        .align(Alignment.BottomEnd)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = movie.nameEn.toString(),
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontSize = 14.sp,
            color = Color(0xFF272727),
            modifier = Modifier.height(15.dp)
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = movie.collectionName,
            color = Color(0xFF838390),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.graphik_regular))
        )

    }
}