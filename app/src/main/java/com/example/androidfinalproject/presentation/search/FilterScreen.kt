package com.example.androidfinalproject.presentation.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun FilterScreen(navController: NavHostController) {
    Log.d("filteer","inn filter screen")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Filter Screen",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.clickable {
                navController.popBackStack() // Возврат к экрану поиска
            }
        )
    }
}
