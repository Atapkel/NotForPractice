package com.example.androidfinalproject.presentation.filmography.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilmographyTypeClickable(
    title: String,
    movieCount: Int,
    onClick: () -> Unit,
    isSelected: Boolean = false
){
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(56.dp))
            .background(if (isSelected) Color.Blue else Color.White)
            .clickable { onClick() }
            .border(1.dp, if (isSelected) Color.Blue else Color(0xff272727), RoundedCornerShape(54.dp))
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 8.dp, bottom = 8.dp),
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 18.sp,
                lineHeight = 19.8.sp,
                color = if (isSelected) Color.White else Color(0xff272727)
            )
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp, top = 12.dp, bottom = 8.dp, end = 24.dp),
            text = "${movieCount}",
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 15.4.sp,
                color = Color(0xffB5B5C9)
            )
        )
    }
}