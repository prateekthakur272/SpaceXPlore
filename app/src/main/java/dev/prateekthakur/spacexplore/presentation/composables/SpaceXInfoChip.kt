package dev.prateekthakur.spacexplore.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SpaceXInfoChip(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    chipColor: Color = MaterialTheme.colorScheme.primary,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(chipColor)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text,
            color = textColor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.W700
        )
    }
}