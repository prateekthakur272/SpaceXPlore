package dev.prateekthakur.spacexplore.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpaceXInfoCard(title: @Composable () -> Unit, icon: (@Composable () -> Unit)? = null, modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit,) {
    SpaceXCard(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(icon != null) {
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                title()
            }
            Spacer(modifier = Modifier.height(16.dp))
            content()
        }
    }
}