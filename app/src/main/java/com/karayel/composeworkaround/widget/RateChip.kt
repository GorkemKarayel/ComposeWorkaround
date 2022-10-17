package com.karayel.composeworkaround.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun RateChip(
    label: String,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    textColor: Color = Color.White,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    textDecoration: TextDecoration? = null,
    shape: Shape = RoundedCornerShape(50),
    iconRes: Painter? = null,
    onChipClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clip(shape)
            .clickable { onChipClick() },
        shape = shape,
        color = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            if (iconRes != null) {
                Icon(
                    modifier = Modifier.size(11.dp),
                    painter = iconRes,
                    tint = Color.White,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(2.dp))
            }

            Text(
                label,
                style = MaterialTheme.typography.button.copy(
                    color = textColor,
                    textDecoration = textDecoration
                )
            )
        }
    }
}
