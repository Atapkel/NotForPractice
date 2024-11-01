package com.example.androidfinalproject.presentation.onBoarding


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.onBoarding.components.OnBoardingPage
import com.example.androidfinalproject.presentation.onBoarding.components.PageIndicator


@Composable
fun OnBoardingScreen(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
    ) {
        val pagerState = rememberPagerState(pageCount = { pages.size })
        Spacer(Modifier.height(80.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
            Text(
                "Пропустить",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                color = Color(0xFFB5B5C9),
                modifier = Modifier.clickable {
                    onClick()
                }
            )
        }

        Spacer(Modifier.height(167.dp))
        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(Modifier.height(56.dp))
        PageIndicator(pagerState.currentPage)
        Spacer(Modifier.height(64.dp))
    }
}