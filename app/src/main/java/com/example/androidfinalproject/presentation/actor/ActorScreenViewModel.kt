package com.example.androidfinalproject.presentation.actor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.data.repository.Repository
import com.example.androidfinalproject.domain.model.Actor
import kotlinx.coroutines.launch

class ActorScreenViewModel(id: Int): ViewModel() {
    private val repository = Repository()
    var state by mutableStateOf<ActorState>(ActorState.Initial)
        private set

    init {
        fetchActor(id)
    }

    private fun fetchActor(id: Int) {
        state = ActorState.Loading
        viewModelScope.launch {
            try {
                val response = repository.getActorDetailById(id)
                if (response.isSuccessful) {
                    state = ActorState.Success(response.body() ?: Actor())
                } else {
                    state = ActorState.Error("Failed to load actor: ${response.message()}")
                }
            } catch (e: Exception) {
                state = ActorState.Error("An error occurred when load actor: ${e.localizedMessage}")
            }
        }
    }
}