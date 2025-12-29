package dev.prateekthakur.spacexplore.presentation.screen.landingPads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXFooterInfo
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPadsScreen(landingPads: List<LandingPad>?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Column {
                TopAppBar(title = {
                    SpaceXTopAppBar(
                        title = "Landing Pads",
                        icon = R.drawable.location_pin_icon
                    )
                })
                HorizontalDivider()
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            when {
                landingPads == null -> LinearProgressIndicator()
                landingPads.isEmpty() -> Text("No Landing Pads Found")
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(landingPads) {
                        LandingPadCard(it, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun LandingPadCard(landingPad: LandingPad, modifier: Modifier = Modifier) {
    SpaceXCard(modifier = modifier) {
        Column {
            AsyncImage(
                model = landingPad.images.large.first(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        landingPad.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SpaceXInfoChip(text = landingPad.status)
                    Spacer(modifier = Modifier.width(8.dp))
                    SpaceXInfoChip(
                        landingPad.type,
                        chipColor = getChitColorForType(landingPad.type)
                    )
                }

                Text(
                    landingPad.fullName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.location_pin_icon),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            "${landingPad.locality}, ${landingPad.region}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.W700
                        )

                        Text(
                            "${landingPad.latitude}°, ${landingPad.longitude}°",
                            color = Color.Gray,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SpaceXFooterInfo(
                            title = "Attempts",
                            value = "${landingPad.landingAttempts}"
                        )
                        Column {
                            Text(
                                "Success",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painterResource(R.drawable.success_icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color(0xFF4CAF50)
                                )
                                Text(
                                    "${landingPad.landingSuccesses}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W700
                                )
                            }
                        }
                        SpaceXFooterInfo(
                            title = "Success Rate",
                            value = "${landingPad.landingSuccesses * 100 / landingPad.landingAttempts}%"
                        )
                    }
                }
            }
        }
    }
}

fun getChitColorForType(type: String): Color {
    return when (type.lowercase(getDefault())) {
        "rtls" -> Color(0xFF2196F3)
        "asds" -> Color(0xFF8BC34A)
        else -> Color.Gray
    }
}
