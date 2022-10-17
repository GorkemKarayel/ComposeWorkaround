package com.karayel.composeworkaround.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun OverlayBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier, content = content) { measurables, constraints ->

        if (measurables.size > 2) throw IllegalStateException("Child count must not be more than 2")

        val placeables = measurables.map { measurable -> measurable.measure(constraints) }
        val totalHeight = placeables.sumOf { placeable -> placeable.height }
        val totalWidth = placeables.maxOf { placeable -> placeable.width }
        val bottomContentHeight = placeables.first().height

        layout(width = totalWidth, height = totalHeight - (bottomContentHeight / 2)) {
            placeables.forEachIndexed { index, placeable ->
                if (index == 1) {
                    placeable.place(x = (totalWidth / 2) - (placeables.last().width / 2), y = 0, zIndex = 8f)
                } else {
                    placeable.place(x = 0, y = placeables.first().height - (bottomContentHeight / 2))
                }
            }
        }
    }
}
