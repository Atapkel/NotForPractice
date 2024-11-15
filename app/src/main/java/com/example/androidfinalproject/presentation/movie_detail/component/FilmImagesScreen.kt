package com.example.androidfinalproject.presentation.movie_detail.component

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
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.ImageOfFilm
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes
import com.example.androidfinalproject.presentation.movie_detail.DetailScreenViewModel


@Composable
fun FilmImagesScreen(images: List<ImageOfFilm>, path: (String) -> Unit, id: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 16.dp)) {
            Image(painter = painterResource(R.drawable.caret_left), contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable { path(HomeRoutes.HOME_DETAIL + "/${id}") })
            Text(
                text = "Галерея",
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
        Spacer(Modifier.height(25.dp))
        LazyVerticalGrid(GridCells.Adaptive(150.dp),
            modifier = Modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(images){image->
                AsyncImage(model = image.imageUrl,contentDescription = "")
            }
        }
        Spacer(Modifier.height(25.dp))
    }
}
