package com.karayel.composeworkaround.screen

import com.karayel.composeworkaround.Event
import com.karayel.composeworkaround.StatefulViewModel
import com.karayel.composeworkaround.UiState
import com.karayel.composeworkaround.launch
import com.karayel.composeworkaround.screen.BooViewModel.BooEvent
import com.karayel.composeworkaround.screen.BooViewModel.BooEvent.NavigateToFoo
import com.karayel.composeworkaround.screen.BooViewModel.BooState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooViewModel @Inject constructor() : StatefulViewModel<BooState, BooEvent>(BooState()) {

    init {
        launch(notifyProgress = false) {
            setState {
                val userList = createUser()
                currentUiState.copy(expert = userList)
            }
        }
    }

    fun navigateToExpertDetail() {
        pushEvent(NavigateToFoo)
    }

    private fun createUser() = Expert(
        name = "Görkem Karayel",
        imageUrl = "https://statinfer.com/wp-content/uploads/dummy-user.png",
        title = "Android Developer",
        rate = "0.0"
    )

    data class BooState(
        val expert: Expert? = null
    ) : UiState

    sealed interface BooEvent : Event {
        object NavigateToFoo : BooEvent
    }

    data class Expert(
        val name: String,
        val imageUrl: String,
        val title: String,
        val rate: String
    )
}
