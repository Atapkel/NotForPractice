package com.example.androidfinalproject.presentation.search.components

import Movie
import MovieCard
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.FilmS
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes
import com.example.androidfinalproject.presentation.search.FilmItem

@Composable
fun MovieCard1(
    filmS: FilmItem,
    navController: NavController
){

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .clickable { navController.navigate(HomeRoutes.HOME_DETAIL+"/${filmS.filmId}") }
    ){
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .width(96.dp)
                .height(132.dp)
        ) {
            AsyncImage(
                model = filmS.posterUrl,
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
                    text = filmS.rating.toString(),
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
        Column (verticalArrangement = Arrangement.spacedBy(4.dp)){
            Text(
                text = filmS.nameRu,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                )
            )
            var genres = ""
            for (genre in filmS.genres) {
                genres += genre.genre + ", "
            }
            Text(
                text = filmS.year.toString()+", $genres",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF838390),
                )
            )
        }
    }
}