package com.example.androidfinalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.androidfinalproject.data.local.RoomInstance
import com.example.androidfinalproject.presentation.graph.RootNavGraph
import com.example.androidfinalproject.presentation.profile.ProfileScreen
import com.example.androidfinalproject.presentation.profile.ProfileScreenViewModel


class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ProfileScreenViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val dao = RoomInstance.getDatabase(applicationContext).dao
                    return ProfileScreenViewModel(dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            RootNavGraph(navController, viewModel)
        }
    }
}


