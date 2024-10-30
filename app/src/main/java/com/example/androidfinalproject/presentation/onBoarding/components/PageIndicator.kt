package com.example.androidfinalproject.presentation.onBoarding.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.onBoarding.pages

@Composable
fun PageIndicator(
    selectedPage: Int,
    selectedColor : Color = Color(0xFF121616),
    unselectedColor: Color =  Color(0xFFD9D9D9)
) {
    Row {
        repeat(pages.size) { index ->
            val color = if (selectedPage == index ) selectedColor else unselectedColor
            Icon(
                painter = painterResource(R.drawable.dot),
                contentDescription = null,
                tint = color,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }
}