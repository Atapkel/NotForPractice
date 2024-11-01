package com.example.androidfinalproject.presentation.genre

import Movie
import MovieCard
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.genre.components.ListPage
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes

@Composable
fun ListScreen(movies: List<Movie>, navController: NavHostController){
    val m = movies.get(0)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp, vertical = 16.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier.align(Alignment.CenterStart).clickable {
                    navController.navigate(HomeRoutes.HOME_MAIN)
                }
            )

            Text(
                text = m.type,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.Center)
            )


        }
        Spacer(Modifier.height(32.dp))
        ListPage(movies, navController)
    }
}

