package dev.prateekthakur.spacexplore.presentation.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dev.prateekthakur.spacexplore.R

@Composable
fun SpaceXBackButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(painterResource(R.drawable.back_arrow_icon), contentDescription = null)
    }
}