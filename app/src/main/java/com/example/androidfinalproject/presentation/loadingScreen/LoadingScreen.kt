package com.example.androidfinalproject.presentation.loadingScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.loadingScreen.components.LoadingIndicator
import kotlinx.coroutines.delay


@Composable
fun LoadingScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)
            .padding(bottom = 26.dp, top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        //Spacer(Modifier.height(80.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.logo),
                contentDescription = null
            )
        }

        //Spacer(modifier = Modifier.height(284.dp))

        LoadingIndicator()

        //Spacer(modifier = Modifier.height(180.dp))

        Image(
            painter = painterResource(R.drawable.onboard1),
            contentDescription = null,
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        )
    }
}