package com.example.androidfinalproject.presentation.movie_detail.component

import Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.domain.model.ImageOfFilm
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes

@Composable
fun DetailScreenOfMovie(
    actors: List<ActorByFilm>,
    movie: Movie,
    images: List<ImageOfFilm>,
    path: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .width(360.dp)
                .height(400.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .clickable {
                        path(HomeRoutes.HOME_MAIN)
                    }
                    .padding(4.dp)
            )

            AsyncImage(
                model = movie.posterUrlPreview,
                modifier = Modifier.fillMaxSize(),
                contentDescription = movie.nameEn
            )
        }


        Text(
            text = movie.nameOriginal.toString(),
            style = TextStyle(
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.graphik_bold)),
                color = Color(0xFF272727),
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(16.dp))
        ShowText("${movie.ratingKinopoisk} ${movie.nameRu}")
        Spacer(Modifier.height(5.dp))
        ShowText("${movie.year} ${movie.genres.get(0).genre} ${movie.type} ")
        Spacer(Modifier.height(5.dp))
        ShowText("${movie.countries.get(0).country} ${movie.filmLength} ${movie.ratingAgeLimits}")
        Spacer(Modifier.height(15.dp))
        Row(
            modifier = Modifier.width(184.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconShower(R.drawable.like_film)
            IconShower(R.drawable.save_film)
            IconShower(R.drawable.eye_film)
            IconShower(R.drawable.share_film)
            IconShower(R.drawable.three_dot_film)
        }
    }

}

@Composable
fun ShowText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.graphik_medium)),
            color = Color(0xFFB5B5C9),
            textAlign = TextAlign.Center,
        )
    )
}


@Composable
fun IconShower(resId: Int, modifier: Modifier=  Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(resId),
        contentDescription = null
    )
}