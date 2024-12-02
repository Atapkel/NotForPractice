package com.example.androidfinalproject.presentation.search.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.androidfinalproject.domain.model.Genre
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.HomeRoutes
import com.example.androidfinalproject.presentation.graph.bottomBarGraphs.SearchRoutes
import com.example.androidfinalproject.presentation.search.FilmFiltersResponse
import com.example.androidfinalproject.presentation.search.FilmFiltersViewModel

@Composable
fun FilterPage(navController: NavHostController,filmFiltersViewModel : FilmFiltersViewModel) {
    val selectedSeeViewmodel by filmFiltersViewModel.selectedSee.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(

            )
    ) {

        Spacer(Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
        ) {
            Image(painter = painterResource(R.drawable.caret_left),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                        navController.popBackStack()
                    })
            Text(
                text = "Настройки поиска",
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
        Spacer(Modifier.height(40.dp))

        val options = listOf("Все", "Фильмы", "Сериалы")
        RadioButtons(filmFiltersViewModel,options,true,"Показывать")


        Spacer(Modifier.height(40.dp))
        SearchParam( "Страна",navController,filmFiltersViewModel)
        SearchParam("Жанр",navController,filmFiltersViewModel)
        Log.d("filterPage", filmFiltersViewModel.selectedCountryId.collectAsState().toString()+ " " + filmFiltersViewModel.selectedGenreId.collectAsState().toString())

        val startDate = filmFiltersViewModel.toYear.collectAsState()
        val endDate = filmFiltersViewModel.fromYear.collectAsState()
        Row(Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp)
            .clickable { navController.navigate(SearchRoutes.DATE) }
            .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Год",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                )
            )
            Text(
                text = if (endDate.value!=0 && startDate.value!=0) "с ${endDate.value} до ${startDate.value}" else "",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF838390),
                    textAlign = TextAlign.Center,
                )
            )
        }
        HorizontalDivider(thickness = 1.dp)

        val startRating by filmFiltersViewModel.startRating.collectAsState()
        val endRating by filmFiltersViewModel.endRating.collectAsState()

        var sliderPosition by remember { mutableStateOf(startRating.toFloat()..endRating.toFloat()) }

        LaunchedEffect(startRating, endRating) {
            sliderPosition = startRating.toFloat()..endRating.toFloat()
        }

        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp)
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Рейтинг",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF272727),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = "",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF838390),
                        textAlign = TextAlign.Center,
                    )
                )
            }

            RangeSlider(
                modifier = Modifier.padding(horizontal = 26.dp),
                value = sliderPosition,
                steps = 100,
                onValueChange = { range ->
                    sliderPosition = range
                },
                valueRange = 0f..10f,
                onValueChangeFinished = {
                    filmFiltersViewModel.setStartRating(sliderPosition.start.toDouble())
                    filmFiltersViewModel.setEndRating(sliderPosition.endInclusive.toDouble())
                    Log.d("rating", "Start: ${sliderPosition.start}, End: ${sliderPosition.endInclusive}")
                },
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTrackColor = Color.Blue, // активная полоска
                    activeTickColor = Color.Blue,// неактивная полоска
                    inactiveTrackColor = Color.LightGray, // неактивные полоса
                    inactiveTickColor = Color.LightGray,// неактивные точки
                    disabledThumbColor = Color.White,
                    disabledActiveTrackColor = Color.White,
                    disabledActiveTickColor = Color.White,
                    disabledInactiveTrackColor = Color.White,
                    disabledInactiveTickColor = Color.White,
                )
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = String.format("%.1f", sliderPosition.start),
                    color = Color.Black
                )
                Text(
                    text = String.format("%.1f", sliderPosition.endInclusive),
                    color = Color.Black
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        HorizontalDivider(thickness = 1.dp)
        Spacer(Modifier.height(20.dp))

        val optionsSort = listOf("Дата", "Популярность", "Рейтинг")
        RadioButtons(filmFiltersViewModel,optionsSort,false,"Сортировать")
        Spacer(Modifier.height(35.dp))
        HorizontalDivider(thickness = 1.dp)
        Spacer(Modifier.height(25.dp))
        var see by remember { mutableStateOf(selectedSeeViewmodel) }
        var size by remember { mutableStateOf(25) }
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .clickable {
                    if (!see) {
                        filmFiltersViewModel.setSelectedSee(true)
                        see = true
                        size = 25

                    } else {
                        filmFiltersViewModel.setSelectedSee(false)
                        see = false
                        size = 28
                    }
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
                Icon(
                    painter = if (!see) painterResource(R.drawable.isseen) else painterResource(R.drawable.eye_icon),
                    contentDescription = null,
                    Modifier.size(size.dp)
                )

            Text(
                text = if (!see)"Не просмотрен" else "Просмотрен",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_regular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                ), modifier = Modifier.padding(start = 20.dp)
            )
        }
//        Column(Modifier.fillMaxWidth()) {
//            Row(Modifier.fillMaxWidth()) {
//
//                Text(text = filmFiltersViewModel.selectedType.collectAsState().value+ "   ")
//                Text(text = filmFiltersViewModel.selectedCountry.collectAsState().value+ " ")
//                Text(text = filmFiltersViewModel.selectedGenre.collectAsState().value)
//            }
//            Row(Modifier.fillMaxWidth()) {
//        Text(text = filmFiltersViewModel.toYear.collectAsState().value.toString()+ "   ")
//        Text(text = filmFiltersViewModel.fromYear.collectAsState().value.toString() + "   ")
//        Text(text = filmFiltersViewModel.startRating.collectAsState().value.toString() + "  ")
//                Text(text = filmFiltersViewModel.endRating.collectAsState().value.toString() +" ")
//            }
//            Row(Modifier.fillMaxWidth()) {
//                Text(text = filmFiltersViewModel.selectedSort.collectAsState().value+ "  ")
//                Text(text = filmFiltersViewModel.selectedSee.collectAsState().value.toString())
//            }
//        }
    }
}

@Composable
fun SearchParam(
    paramName: String,
    navController: NavHostController,
    filtersViewModel: FilmFiltersViewModel
) {
    val selectedCountry by filtersViewModel.selectedCountry.collectAsState()
    val selectedGenre by filtersViewModel.selectedGenre.collectAsState()
    Row(Modifier
        .fillMaxWidth()
        .padding(horizontal = 26.dp)
        .clickable {
            if (paramName == "Страна"){
                navController.navigate(SearchRoutes.COUNTRY)
            }else{
                navController.navigate(SearchRoutes.GENRE)
            }
        }
        .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = paramName,
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF272727),
                textAlign = TextAlign.Center,
            )
        )
        Text(
            text = if(paramName == "Страна") selectedCountry else  selectedGenre ,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontWeight = FontWeight(400),
                color = Color(0xFF838390),
                textAlign = TextAlign.Center,
            )
        )
    }
    HorizontalDivider(thickness = 1.dp)
}


@Composable
fun RadioButtons(filtersViewModel: FilmFiltersViewModel,options: List<String>, format: Boolean, topic: String){
    val selected by if(format){
        filtersViewModel.selectedType.collectAsState()
    }else{
        filtersViewModel.selectedSort.collectAsState()
    }
    Text(
        text = topic,
        style = TextStyle(
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.graphik_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF838390),
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier.padding(horizontal = 26.dp)
    )
    Spacer(Modifier.height(20.dp))
    Row(
        Modifier
            .fillMaxWidth()
            .height(35.dp)
            .padding(horizontal = 26.dp)
    ) {
        options.forEachIndexed { index, option ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()

                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = if (index == 0) RoundedCornerShape(
                            bottomStart = 30.dp,
                            topStart = 30.dp
                        )
                        else if (index == 2) RoundedCornerShape(
                            topEnd = 30.dp,
                            bottomEnd = 30.dp
                        )
                        else RoundedCornerShape(0.dp)

                    )
                    .background(
                        color = if (selected == option) Color(0xFF3D3BFF) else Color.Transparent,
                        shape = if (index == 0) RoundedCornerShape(
                            bottomStart = 30.dp,
                            topStart = 30.dp
                        )
                        else if (index == 2) RoundedCornerShape(
                            topEnd = 30.dp,
                            bottomEnd = 30.dp
                        )
                        else RoundedCornerShape(0.dp),

                        )
                    .clickable {

                        if (format){
                            filtersViewModel.setSelectedType(option)
                        }else{
                            filtersViewModel.setSelectedSort(option)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.graphik_medium)),
                        fontWeight = FontWeight(500),
                        color = if (selected == option) Color.White else Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

        }

    }
}