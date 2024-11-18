package com.example.androidfinalproject.presentation.actor.component

import Movie
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.androidfinalproject.domain.model.Actor
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes

@Composable
fun ActorScreenDetails(actor: Actor, filmCount: Int,movies: List<Movie>,path: (String) -> Unit,) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(12.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier.clickable {path("Back")}
            )

        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box (
                modifier = Modifier
                    .background(color = Color(0x66B5B5C9))
                    .clip(RoundedCornerShape(4.dp))
            ){
                AsyncImage(
                    model = actor.posterUrl,
                    contentDescription = actor.nameRu,
                    )
            }
            Column (
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    text = actor.nameRu,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_bold)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF272727),
                    )
                )
                Text(
                    text = actor.profession,
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF838390),
                    )
                )

            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        ListRow("Лучшее","Все",false,filmCount)
        Spacer(modifier = Modifier.height(24.dp))
        FilmRow(movies,path)
        Spacer(modifier = Modifier.height(36.dp))
        ListRow("Фильмография","К списку",true,filmCount)
    }
}

@Composable
fun ListRow(mainText:String, text:String, isFilmography: Boolean,count:Int){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = mainText,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_bold)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                )
            )
            if (isFilmography){
                Text(
                    text = "$count фильма",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF838391),
                    )
                )

            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {}
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3D3BFF),
                    textAlign = TextAlign.Center,
                )
            )
            Image(
                painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier
                    .graphicsLayer(rotationZ = 180f)
                    .size(18.dp),
                colorFilter = ColorFilter.tint(Color(0xFF3D3BFF))
            )
        }
    }
}

@Composable
fun FilmRow(movies: List<Movie>,path: (String) -> Unit) {
    Row {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(movies) { movie ->
                ActorMovieCard(
                    movie = movie,
                    onClick = { path(HomeRoutes.HOME_DETAIL+"/${movie.kinopoiskId}") }
                )
            }
            item {
                Column(
                    modifier = Modifier
                        .width(111.dp)
                        .height(194.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(50))
                            .size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_right),
                            tint = Color(0xFF3D3BFF),
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "Показать все",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        color = Color(0xFF272727)
                    )
                }
            }
        }
    }
}
