package com.example.androidfinalproject.presentation.search.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
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
import com.example.androidfinalproject.presentation.search.FilmFiltersViewModel

@Composable
fun DataPage(navController: NavHostController, viewModel: FilmFiltersViewModel) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 26.dp)) {
        Spacer(Modifier.height(25.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
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
                text = "Период",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF272727),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
            Spacer(Modifier.height(50.dp))
        }
        YearPicker("Искать в период с", viewModel, true)
        Spacer(Modifier.height(50.dp))
        YearPicker("Искать в период до", viewModel, false)
        Spacer(Modifier.height(60.dp))
        Button(
            onClick = {
                if (viewModel.toYear.value>viewModel.fromYear.value && viewModel.toYear.value!=0 && viewModel.fromYear.value!=0) {
                    navController.popBackStack()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3D3BFF)
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Выбрать",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.graphik_medium)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun YearPicker(
    label: String,
    viewModel: FilmFiltersViewModel,
    format: Boolean
) {

    val selectedYear by if (format) {
        viewModel.fromYear.collectAsState()
    } else {
        viewModel.toYear.collectAsState()
    }
    var startYear by remember { mutableStateOf(1980) }
    val years = (startYear until startYear + 12).toList()
    Column {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.graphik_regular)),
                fontWeight = FontWeight(400),
                color = Color.Gray
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Spacer(Modifier.height(10.dp))
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (startYear + 11 > 2024) "$startYear - 2024" else "$startYear - ${startYear + 11}",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3D3BFF)
                        )
                    )
                    Row {
                        Image(
                            painter = painterResource(R.drawable.caret_left),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    if (startYear - 12 >= 1980) {
                                        startYear -= 12
                                    }
                                }
                        )
                        Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(R.drawable.caret_left),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(180f)
                                .clickable {
                                    if (startYear + 12 <= 2024) {
                                        startYear += 12
                                    }
                                }
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                Column {
                    for (row in years.chunked(3)) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            row.forEach { year ->
                                if (year in 1980..2024) {
                                    Text(
                                        text = "$year",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (year == selectedYear) Color(0xFF3D3BFF) else Color.Black
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clickable {
                                                if (format) {
                                                    viewModel.setFromYear(year)
                                                } else {
                                                    viewModel.setToYear(year)
                                                }
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
