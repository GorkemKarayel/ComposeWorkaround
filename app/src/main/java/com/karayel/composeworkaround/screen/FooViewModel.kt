package com.karayel.composeworkaround.screen

import com.karayel.composeworkaround.Event.NoEvent
import com.karayel.composeworkaround.StatefulViewModel
import com.karayel.composeworkaround.UiState
import com.karayel.composeworkaround.launch
import com.karayel.composeworkaround.screen.FooViewModel.FooState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FooViewModel @Inject constructor() : StatefulViewModel<FooState, NoEvent>(FooState()) {
    init {
        launch(notifyProgress = false) {
            setState {
                val dummyUserList = createUserList()
                currentUiState.copy(userList = dummyUserList.toMutableList())
            }
        }
    }

    fun changeUserName(s: String, index: Int) {
        if (index == 5) {
            throwAnything()
            return
        }

        setState {
            val newName = (s.toInt() + 100).toString()
            val updateList = currentUiState.userList.mapIndexed { _index, user ->
                if (_index == index) {
                    user.copy(userName = newName)
                } else {
                    user
                }
            }
            currentUiState.copy(userList = updateList.toMutableList())
        }
    }

    private fun throwAnything() {
        setError(Exception("Example"))
    }

    private fun createUserList(): List<FooUser> {
        return Array(40) {
            FooUser("$it", it)
        }.toList()
    }

    data class FooState(
        val userList: MutableList<FooUser> = mutableListOf(),
        val userSurname: String = ""
    ) : UiState

    data class FooUser(
        val userName: String,
        val userAge: Int
    )
}
