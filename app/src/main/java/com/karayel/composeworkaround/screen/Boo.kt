package com.karayel.composeworkaround.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.karayel.composeworkaround.core.FooNavigation
import com.karayel.composeworkaround.widget.OverlayBox
import com.karayel.composeworkaround.widget.RateChip
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Boo(
    modifier: Modifier = Modifier,
    navController: NavController,
    booViewModel: BooViewModel = viewModel()) {

    val flow by booViewModel.stateFlow.collectAsState()
    val expert: BooViewModel.Expert? = remember { flow.uiState.expert }

    LaunchedEffect(key1 = booViewModel) {
        booViewModel.eventFlow.collectLatest { booEvent ->
            when (booEvent) {
                BooViewModel.BooEvent.NavigateToFoo -> {
                    navController.navigate(FooNavigation("12").route)
                }
            }
        }
    }

    OverlayBox(
        modifier = Modifier
            .padding(24.dp)
            .background(color = Color.LightGray)
    ) {
        val height = 179
        Card(
            modifier = Modifier
                .width(198.dp)
                .height(height.dp)
                .clickable { booViewModel.navigateToExpertDetail() },
            shape = RoundedCornerShape(8),
            backgroundColor = Color.White
        ) {
        }
        Column {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(expert?.imageUrl),
                    contentDescription = "Image",
                    modifier = Modifier
                        .width(166.dp)
                        .height(height.dp)
                        .clip(RoundedCornerShape(55))
                        .clickable { booViewModel.navigateToExpertDetail() },
                    contentScale = ContentScale.Crop,
                )
                RateChip(
                    modifier = Modifier
                        .padding(8.dp)
                        .offset(x = 16.dp, y = (-16).dp)
                        .align(Alignment.TopEnd),
                    shape = RoundedCornerShape(16.dp),
                    label = expert?.rate.orEmpty()
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = expert?.name.orEmpty(),
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = expert?.title.orEmpty(), color = Color.Black)
        }
    }
}
