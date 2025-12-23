package dev.prateekthakur.spacexplore.presentation.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpaceXCard(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.border(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.surfaceDim,
            shape = MaterialTheme.shapes.medium
        ), content = content
    )
}