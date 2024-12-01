package com.example.androidfinalproject.presentation.movie_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes


@Composable
fun ActorCard(actor: ActorByFilm,path: (String) -> Unit) {
    Row(
        Modifier
            .fillMaxSize()
            .clickable { path(HomeRoutes.ACTOR_INFO + "/${actor.staffId}")},
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = actor.posterUrl,
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(70.dp)
                .width(50.dp)
                .clip(RoundedCornerShape(size = 4.dp))

        )
        Column(
            modifier = Modifier.padding(horizontal = 18.dp)
        ) {
            Text(
                text = actor.nameRu,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF272727),
                )
            )
            Text(
                text = actor.professionText,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF838390),
                )
            )
        }
    }
}