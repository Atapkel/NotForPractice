package com.example.androidfinalproject.presentation.profile

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.dto.MovieDTO

private val gradient = Brush.verticalGradient(
    colors = listOf(
        Color(0x001B1B1B),
        Color(0xFF1B1B1B),
    )
)

@Composable
fun MovieDetailSavedScreen(
    viewModel: ProfileScreenViewModel,
    id: Int,
    path: (String) -> Unit,
) {


//    println("details of movie with id $id")
    val movie: MovieDTO? = viewModel.state.collectAsState().value.movies.find {
        it.id == id
    }
    if (movie == null){
        Text(text = "Movie not found!")
        return
    }
//    println("test for movie detail with name ${movie.nameRu}")
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
                        .align(Alignment.BottomCenter),
                )
//                println("images works with ${movie.posterUrl}")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradient)
                )
                Image(contentDescription = "",
                    painter = painterResource(R.drawable.caret_left),
                    modifier = Modifier
                        .clickable {
                            path("popBackStack")
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
                            text = movie.nameRu+".",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFB5B5C9),
                            )
                        )
                    }
                    var lengthOfMovie = ""
                    if (movie.filmLength / 60 > 0) {
                        lengthOfMovie += (movie.filmLength / 60).toString() + " ч"
                    } else {
                        lengthOfMovie += movie.filmLength.toString() + " мин"
                    }

                    val ageLimit = movie.ratingAgeLimits
                    if (!ageLimit.isNullOrEmpty() && ageLimit.length > 3) {
                        lengthOfMovie += ", " + ageLimit.substring(3) + "+"
                    }else{
                        lengthOfMovie += ", " + "No age limit"
                    }


                    Text(

                        text = movie.countries + ", " + lengthOfMovie,
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
                        Image(contentDescription = "", painter = painterResource(R.drawable.liked), modifier = Modifier.clickable {



                        })
                        Image(contentDescription = "", painter = painterResource(R.drawable.saved), modifier = Modifier.clickable {

                        })
                        Image(contentDescription = "", painter = painterResource(R.drawable.isseen), modifier = Modifier.clickable {  })
                        Image(contentDescription = "", painter = painterResource(R.drawable.share), modifier = Modifier.clickable {  })
                        Image(
                            contentDescription = "",
                            painter = painterResource(R.drawable.threedots), modifier = Modifier.clickable {  }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 45.dp)
            ) {

                if (!movie.shortDescription.isNullOrEmpty()) {
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
                if (!movie.description.isNullOrEmpty()) {

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
            }
        }
    }
}



