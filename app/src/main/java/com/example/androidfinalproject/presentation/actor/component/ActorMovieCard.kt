package com.example.androidfinalproject.presentation.actor.component

import Movie
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.androidfinalproject.R

@Composable
fun ActorMovieCard(movie: Movie) {
    Column(
        modifier = Modifier
            .width(111.dp)
            .height(194.dp)
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
                contentDescription = ""
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
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = movie.nameOriginal.toString(),
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = movie.type,
            color = Color(0xFF838390),
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.graphik_regular))
        )
    }
}
