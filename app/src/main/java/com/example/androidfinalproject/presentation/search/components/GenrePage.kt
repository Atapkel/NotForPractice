package com.example.androidfinalproject.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.androidfinalproject.R
import com.example.androidfinalproject.domain.model.Country
import com.example.androidfinalproject.domain.model.Genre
import com.example.androidfinalproject.presentation.search.FilmFiltersViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenrePage(
    genres: List<Genre>,
    navController: NavHostController,
    filtersViewModel: FilmFiltersViewModel
) {
    var filteredCountries by remember { mutableStateOf(genres) }
    Column(Modifier.fillMaxSize()) {
        Spacer(Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
        ) {
            Spacer(Modifier.height(30.dp))
            Image(painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {

                        navController.popBackStack()
                    })
            Text(
                text = "Страна",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(Modifier.height(30.dp))
        var keyword by remember { mutableStateOf("") }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(
                        color = Color(0x66B5B5C9),
                        shape = RoundedCornerShape(size = 56.dp)
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.Gray),
                    modifier = Modifier.size(16.dp)
                )
                TextField(
                    value = keyword,
                    onValueChange = { value ->
                        keyword = value
                        filteredCountries = if (value.isEmpty()) {
                            genres
                        } else {

                            genres.filter { it.genre.startsWith(value, ignoreCase = true) }
                        }
                    },
                    placeholder = {
                        Text(text = "Введите страну", color = Color.Gray)
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
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
        Spacer(Modifier.height(50.dp))
        HorizontalDivider(thickness = 1.dp)
        var selectedCountry by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            filteredCountries.forEach { genre ->
                if (genre.genre.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedCountry = genre.genre
                                filtersViewModel.setSelectedGenre(genre.genre)
                                filtersViewModel.setSelectedGenreId(genre.id)
                                navController.popBackStack()
                            }
                            .background(
                                if (selectedCountry == genre.genre) Color(0x4DB5B5C9) else
                                    Color.White
                            )
                    ) {
                        Text(
                            text = genre.genre,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF272727),
                                textAlign = TextAlign.Center,
                            ),
                            modifier = Modifier
                                .padding(horizontal = 26.dp, vertical = 16.dp)
                        )
                    }
                    HorizontalDivider(thickness = 1.dp)
                }
            }
        }
    }
}