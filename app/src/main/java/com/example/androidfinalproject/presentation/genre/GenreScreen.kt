package com.example.androidfinalproject.presentation.genre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.genre.components.ListPage

@Composable
fun GenreScreen(type: String, path: (String) -> Unit) {
    val viewModel: GenreModelView = remember { GenreModelView(type) }
    when (val state = viewModel.state) {
        is GenreState.Initial -> {
        }

        is GenreState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color(0xFF3D3BFF))
            }
        }

        is GenreState.Success -> {
            ListScreen(state.data, path)
        }

        is GenreState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.eye_icon),
                    contentDescription = "Error",
                    modifier = Modifier.size(25.dp)
                )
                Text(text = state.message, color = Color.Red)
            }
        }
    }
}