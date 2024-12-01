package com.example.androidfinalproject.presentation.search
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    var keyword by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 26.dp)
    ) {
        Row (

        ){  }
        TextField(
            value = keyword,
            onValueChange = { newValue ->
                keyword = newValue
                viewModel.searchFilm(newValue)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search for films") },
            placeholder = { Text("Enter film name...") },
            singleLine = true
        )
    }

    when (state) {
        is SearchState.Initial -> {
            // Initial state, maybe show nothing
        }
        is SearchState.Loading -> {
            // Show loading indicator
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(color = Color(0xFF3D3BFF))
            }
        }
        is SearchState.Success -> {
            LazyColumn {
                items((state as SearchState.Success).films) { film ->
                    Text(film.nameRu ?: "Unknown Title")
                }
            }
        }
        is SearchState.Error -> {
            // Show error message
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            }
        }
    }
}