package com.example.androidfinalproject.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.dto.MovieDTO
import com.example.androidfinalproject.presentation.movie_detail.DetailScreenViewModel
import com.example.androidfinalproject.presentation.profile.event.MovieEvent
import com.example.androidfinalproject.presentation.profile.event.MovieState

@Composable
fun ProfileScreen(viewModel: ProfileScreenViewModel) {
    val state = viewModel.state.collectAsState().value
    val movies = state.movies


    if (movies.isEmpty()) {
        Text("No movies saved yet.")
    } else {
        LazyColumn {
            items(movies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }
    Button(onClick = {
        println("delete button clicked")
        viewModel.onEvent(MovieEvent.DeleteMovie)
    }) {
        Text("DELETE ALL")
    }
}

@Composable
fun MovieItem(movie: MovieDTO) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Name: ${movie.nameRu} (${movie.year})")
        Text(text = "Genres: ${movie.genres}")
        Text(text = "Rating: ${movie.ratingKinopoisk}")
        Text(text = "Description: ${movie.shortDescription}")


    }
}


//@Preview(showBackground = true)
@Composable
fun Collections() {
    var list = listOf(
        Colllection("Любимые", R.drawable.liked, 10),
        Colllection("Хочу посмотреть", R.drawable.saved, 20)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Коллекции",
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF272727),
            )
        )

//        Icon(painter = painterResource(R.drawable.plus),
//            contentDescription = null,
//            modifier = Modifier.size(8.dp))
        Spacer(Modifier.height(27.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(8.dp)
        ) {
            items(list) { collection ->
                ColllectionCard(collection)
            }
        }
    }
}

data class Colllection(val title: String, val iconId: Int, val count: Int)

@Composable
fun ColllectionCard(collection: Colllection) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp))
            .size(146.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(collection.iconId),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = collection.title,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF272727),
                )
            )
            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(17.dp)
                    .background(color = Color(0xFF3D3BFF), shape = RoundedCornerShape(size = 16.dp))
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
            ) {
                Text(
                    text = "${collection.count}",
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }

        }
    }
}
















