package com.example.androidfinalproject.presentation.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.SearchRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    navController: NavHostController,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color(0x66B5B5C9), shape = RoundedCornerShape(size = 56.dp))
                .padding(horizontal = 16.dp)

        ) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "image description",
                colorFilter = ColorFilter.tint(color = Color.Gray),
                modifier = Modifier.size(16.dp)
            )
            TextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = {
                    Text(text = "Фильмы, актёры, режиссёры", color = Color.Gray)
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Gray
                ),
                modifier = Modifier.weight(1f)
            )
            Box(
                Modifier
                    .width(1.dp)
                    .height(16.dp)
                    .background(color = Color(0xFF838390))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(R.drawable.filter),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        Log.d("filter_nav", "in filter clickable")
                        navController.navigate(SearchRoutes.FILTER)
                    }
            )
        }
    }
}

@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel: SearchViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    var keyword by remember { mutableStateOf("") }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp)
    ) {
        SearchBar(
            navController = navController,
            query = keyword,
            onQueryChange = { newValue ->
                keyword = newValue
                viewModel.searchFilm(newValue)
            }
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