package com.example.androidfinalproject.presentation.movie_detail

import Movie
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ControlledComposition
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


@Composable
fun DetailPage(data: Movie) {
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
            Text(text = data.nameOriginal.toString())
            Text(text = data.ratingKinopoisk.toString() + " " + data.nameRu)
            var genres = ""
            for(genre in data.genres){
                genres += genre.genre + ", "
            }
            Text(text = data.year.toString() + " " + genres.substring(0,genres.length-2))
            Text(text = data.countries[0].country + " \n\n" + data)

        }
    }
}
