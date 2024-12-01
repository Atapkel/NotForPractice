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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.SearchRoutes
import com.example.androidfinalproject.presentation.search.components.MovieCard1
import com.example.androidfinalproject.presentation.search.components.SearchBar
import kotlinx.coroutines.delay as delay



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


        when (state) {
            is SearchState.Initial -> {
            }

            is SearchState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = Color(0xFF3D3BFF))
                }
            }

            is SearchState.Success -> {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 24.dp)
                ){
                    items((state as SearchState.Success).films) { film ->
                        MovieCard1(
                            filmS = film,
                            navController = navController
                        )
                    }
                }
            }

            is SearchState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if ((state as SearchState.Error).message == "No films found") {
                        Text(
                            text = "К сожалению, по вашему запросу  ничего не найдено",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF272727),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }else{
                        Text(
                            text = "Failed to load data",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF272727),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }
        }
    }
}