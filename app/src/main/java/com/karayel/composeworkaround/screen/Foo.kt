package com.karayel.composeworkaround.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Foo(
    modifier: Modifier = Modifier,
    userId: String,
    navController: NavController,
    fooViewModel: FooViewModel = viewModel()
) {

    val flow by fooViewModel.stateFlow.collectAsState()
    val listState = rememberLazyListState()

    Column(Modifier.padding(24.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "User Id = $userId",
            style = MaterialTheme.typography.body1.copy(color = Color.Red)
        )
        Spacer(modifier = Modifier.padding(top = 50.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            state = listState
        ) {
            itemsIndexed(flow.uiState.userList) { index, item ->
                UserButton(userName = item.userName) {
                    fooViewModel.changeUserName(it, index)
                }
            }
        }
    }
}

@Composable
fun UserButton(userName: String, onClicked: (String) -> Unit) {
    Button(onClick = { onClicked(userName) }) {
        Text(text = userName)
    }
}
