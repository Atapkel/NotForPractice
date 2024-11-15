package com.example.androidfinalproject.presentation.movie_detail.component

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.ActorByFilm
import com.example.androidfinalproject.domain.model.ImageOfFilm
import com.example.androidfinalproject.domain.model.SimilarFilm
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes


private val gradient = Brush.verticalGradient(
    colors = listOf(
        Color(0x001B1B1B),
        Color(0xFF1B1B1B),
    )
)



@Composable
fun DetailScreenOfMovie(
    stuffs: List<ActorByFilm>,
    movie: Movie,
    images: List<ImageOfFilm>,
    similar: List<SimilarFilm>,
    path: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Box(
                modifier = Modifier
                    .height(600.dp)
                    .fillMaxWidth()
            ) {

                Image(
                    painter = rememberImagePainter(movie.posterUrl),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                )
                Image(contentDescription = "",
                    painter = painterResource(R.drawable.caret_left),
                    modifier = Modifier
                        .clickable {
                            path(HomeRoutes.HOME_MAIN)
                        }
                        .padding(horizontal = 30.dp, vertical = 20.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = movie.ratingKinopoisk.toString() + "  ",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFB5B5C9),
                            )
                        )
                        Text(
                            text = movie.nameRu,
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFB5B5C9),
                            )
                        )
                    }

                    var genres = ""
                    for (genre in movie.genres) {
                        genres += genre.genre + ", "
                    }
                    Text(
                        text = movie.year.toString() + ", " + genres.substring(
                            0,
                            genres.length - 2
                        ),
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFB5B5C9),
                        ),
                        modifier = Modifier.padding(3.dp)
                    )


                    var lengthOfMovie = ""
                    if (movie.filmLength / 60 > 0) {
                        lengthOfMovie += (movie.filmLength / 60).toString() + " ч"
                    } else {
                        lengthOfMovie += movie.filmLength.toString() + " мин"
                    }

                    val ageLimit = movie.ratingAgeLimits
                    if (ageLimit != null) {
                        lengthOfMovie += ", " + ageLimit.substring(3) + "+"
                    }


                    Text(

                        text = movie.countries[0].country + ", " + lengthOfMovie,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFFB5B5C9)
                        ),
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Row(
                        Modifier.padding(top = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(17.dp)
                    ) {
                        Image(contentDescription = "", painter = painterResource(R.drawable.liked))
                        Image(contentDescription = "", painter = painterResource(R.drawable.saved))
                        Image(contentDescription = "", painter = painterResource(R.drawable.isseen))
                        Image(contentDescription = "", painter = painterResource(R.drawable.share))
                        Image(
                            contentDescription = "",
                            painter = painterResource(R.drawable.threedots)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 45.dp)
            ) {
                if (movie.shortDescription != null) {
                    Text(
                        text = movie.shortDescription,
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_bold)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF272727),
                        )
                    )
                    Spacer(Modifier.height(30.dp))
                }
                if (movie.description != null) {

                    Text(
                        text = movie.description,
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_regular)),
                            fontWeight = FontWeight(400),
                            color = Color(0xFF272727),
                        )
                    )
                    Spacer(Modifier.height(40.dp))
                }


                val directors = mutableListOf<ActorByFilm>()
                val actors = mutableListOf<ActorByFilm>()

                for (stuff in stuffs) {
                    if (stuff.professionKey == "ACTOR" && stuff.nameRu != null) {
                        actors.add(stuff)
                    } else {
                        directors.add(stuff)
                    }
                }
                StuffLists(actors, "В фильме снимались",path)
                StuffLists(directors, "Над фильмом работали",path)
                ImageGallery(images,path,movie.kinopoiskId)
                SimilarFilms(similar, path)
            }
        }
    }
}



@Composable
fun Header(topic: String, list: List<Any>,onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF272727),
            )
        )
        Row(
            modifier = Modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = list.size.toString(), style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF3D3BFF),
            ))
            RotatedCaret()
        }
    }
}

@Composable
fun ImageGallery(images: List<ImageOfFilm>, path: (String) -> Unit, kinopoiskId: Int) {
    if (images.isNotEmpty()) {
        Column(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Header("Галерея", images, {path(HomeRoutes.FILM_IMAGES + "/${kinopoiskId}")})
            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                items(images) { image ->
                    AsyncImage(
                        model = image.imageUrl,
                        contentDescription = "",
                        modifier = Modifier
                            .height(400.dp)
                            .width(200.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SimilarFilms(similar: List<SimilarFilm>, path: (String) -> Unit) {
    if (similar.isNotEmpty()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Похожие фильмы",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF272727),
                    )
                )
                Row(
                    modifier = Modifier.clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = similar.size.toString(),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF3D3BFF),
                        )
                    )
                    RotatedCaret()
                }
            }
            Spacer(Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)

            ) {
                items(similar) { similarFilm ->
                    SimilarCard(similarFilm, onClick = {
                        path(HomeRoutes.HOME_DETAIL + "/${similarFilm.filmId}")
                    })
                }
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}


@Composable
fun SimilarCard(similarFilm: SimilarFilm, onClick: () -> Unit) {
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
                model = similarFilm.posterUrl,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
        }
        Spacer(Modifier.height(8.dp))
        Text(
            text = similarFilm.nameOriginal.toString(),
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontSize = 14.sp,
            color = Color(0xFF272727)
        )
        Spacer(Modifier.height(2.dp))
    }
}


@Composable
fun StuffLists(stuffs: List<ActorByFilm>, topic: String,path: (String) -> Unit) {
    if (stuffs.isNotEmpty()) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = topic,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF272727),
                    )
                )
                Row(
                    modifier = Modifier.clickable { },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stuffs.size.toString(),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.graphik_medium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF3D3BFF),
                        )
                    )
                    RotatedCaret()
                }
            }
            Spacer(Modifier.height(32.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)

            ) {

                items(stuffs.chunked(2)) { sortedActor ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        sortedActor.forEach { actor ->
                            ActorCard(actor,path)
                        }
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}


@Composable
fun RotatedCaret() {
    Image(
        painter = painterResource(R.drawable.caret_left),
        contentDescription = "",
        modifier = Modifier
            .graphicsLayer(rotationZ = 180f)
            .size(18.dp),
        colorFilter = ColorFilter.tint(Color(0xFF3D3BFF))
    )
}