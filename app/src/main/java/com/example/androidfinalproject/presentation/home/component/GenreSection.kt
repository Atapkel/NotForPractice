package com.example.androidfinalproject.presentation.home.component

import Movie
import MovieCard
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes

@Composable
fun GenreSection(movies: List<Movie>, path: (String) -> Unit) {
    Column {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = movies.get(0).type,
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 18.sp,
                color = Color(0xFF272727)
            )

            Text(
                text = "Все",
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                fontSize = 14.sp,
                color = Color(0xFF3D3BFF),
                modifier = Modifier.clickable {
                    path(HomeRoutes.HOME_SEE_ALL+"/${movies.get(0).type}")
                }
            )
        }

        Spacer(Modifier.height(24.dp))
        Row {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(movies) { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = {
                            path(HomeRoutes.HOME_DETAIL+"/${movie.kinopoiskId}")

                        }
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
                                .clip(CircleShape)
                                .size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_right),
                                tint = Color(0xFF3D3BFF),
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    path(HomeRoutes.HOME_SEE_ALL+"/${movies.get(0).type}")
                                }
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

}
