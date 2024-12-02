package com.example.androidfinalproject.presentation.search.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
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
                        navController.navigate(SearchRoutes.FILTER)
                    }
            )
        }
    }
}