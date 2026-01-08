package dev.prateekthakur.spacexplore.presentation.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.navigation.AppRoute

@Composable
fun HomeScreen(onTileClick: (AppRoute) -> Unit) {
    Scaffold(containerColor = MaterialTheme.colorScheme.surfaceContainer) { paddingValues ->

        val rotation by rememberInfiniteTransition().animateFloat(
            initialValue = -15f,
            targetValue = 15f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "wobble"
        )

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 16.dp)
                    .background(
                        brush = Brush.linearGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary,
                                MaterialTheme.colorScheme.primary,
                            )
                        )
                    )
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(24.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(R.drawable.rocket_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .size(40.dp)
                                .rotate(rotation)
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Column {
                            Text(
                                "Welcome,",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                            Text(
                                "SpaceX API",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Explore comprehensive information about SpaceX missions, vehicles, crew, and facilities",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                HomeScreenOptions.forEach {
                    HomeScreenTile(it, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
                        onTileClick(it.route)
                    }
                }
            }

        }
    }
}

@Composable
fun HomeScreenTile(
    option: HomeScreenTileOption,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    SpaceXCard(modifier = modifier) {
        Box(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.large)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(16.dp)
                ) {
                    Icon(painterResource(option.icon), contentDescription = null, tint = MaterialTheme.colorScheme.onSurface)
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(option.title, style = MaterialTheme.typography.titleMedium)
                    Text(option.subtitle, style = MaterialTheme.typography.bodyMedium)
                }

                Icon(painterResource(R.drawable.open_link_icon), contentDescription = null)
            }
        }
    }
}