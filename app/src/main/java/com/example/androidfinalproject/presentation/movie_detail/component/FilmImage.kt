package com.example.androidfinalproject.presentation.movie_detail.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.androidfinalproject.domain.model.ImageOfFilm
import com.example.androidfinalproject.presentation.movie_detail.DetailScreenViewModel
import com.example.androidfinalproject.presentation.movie_detail.FilmImagesState

@Composable
fun FilmImage(id: Int, path: (String) -> Unit) {
    val viewModel: DetailScreenViewModel = remember { DetailScreenViewModel(id) }
    when (val state = viewModel.stateOfImagesByFilm) {
        is FilmImagesState.Initial -> {}
        is FilmImagesState.Error -> {}
        is FilmImagesState.Loading -> {}
        is FilmImagesState.Success -> {
            val images: List<ImageOfFilm> = state.images;
            FilmImagesScreen(images, path,id)
        }
    }
}