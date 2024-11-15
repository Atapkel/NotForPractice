package com.example.androidfinalproject.presentation.actor

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
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.actor.component.ActorScreenDetails

@Composable
fun ActorScreen(id: Int,path: (String) -> Unit) {
    val viewModel: ActorScreenViewModel = remember { ActorScreenViewModel(id) }

    when (val state = viewModel.state) {
        is ActorState.Initial -> {
        }

        is ActorState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color(0xFF3D3BFF))
            }
        }

        is ActorState.Success -> {
            ActorScreenDetails(state.actor,state.filmCount,state.movies,path)
        }
        is ActorState.Error -> {
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