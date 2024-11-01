package com.example.androidfinalproject.presentation.onBoarding.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.onBoarding.Page


@Composable
fun OnBoardingPage(page: Page) {
    Column {
        Image(
            painter = painterResource(page.imageId),
            contentDescription = null,
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )
        Spacer(Modifier.height(68.dp))
        Text(
            page.title,
            style = TextStyle(
                lineHeight = 35.2.sp,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.graphik_regular))
            ),
        )
    }
}