package com.example.androidfinalproject.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.example.androidfinalproject.R

@Composable
fun ProfileScreen() {

}

@Composable
fun Collections(){
    Column {
        Text(text = "Коллекции")
        Icon(imageVector = painterResource(R.drawable.plus),
            contentDescription = null)
    }
}