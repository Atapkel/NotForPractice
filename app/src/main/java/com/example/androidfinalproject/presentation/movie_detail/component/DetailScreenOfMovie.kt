package com.example.androidfinalproject.presentation.movie_detail.component

import Movie
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.ActorByFilm

@Composable
fun DetailScreenOfMovie(actors: List<ActorByFilm>, movie: Movie) {
    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            modifier = Modifier.
//                fillMaxWidth()
//                .graphicsLayer(
//                    translationY = -400f
//                ),
//            painter = rememberImagePainter(data.posterUrl),
//            contentDescription = "Image loaded from URL",
//            contentScale = ContentScale.Crop,
//        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Text(text = movie.nameOriginal.toString())
            Text(text = movie.ratingKinopoisk.toString() + " " + movie.nameRu)
            var genres = ""
            for(genre in movie.genres){
                genres += genre.genre + ", "
            }
            Text(text = movie.year.toString() + ", " + genres.substring(0,genres.length-2),
                    style = TextStyle(
                    fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFFB5B5C9),
                textAlign = TextAlign.Center,
            )
            )
            var lengthOfMovie = ""
            if (movie.filmLength/60 > 0){
                lengthOfMovie += (movie.filmLength/60).toString() + " ч " + movie.filmLength%60 + " мин, +"  + movie.ratingAgeLimits.substring(3)
            }else{
                lengthOfMovie += movie.filmLength.toString() + " мин, +" + movie.ratingAgeLimits.substring(3)
            }

            Text(text = movie.countries[0].country + ", ")

        }
    }
}