package dev.prateekthakur.spacexplore.presentation.screen.landingPads

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXBackButton
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.openBrowserCustomTab
import dev.prateekthakur.spacexplore.utils.openInGoogleMaps
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPadDetailScreen(landingPad: LandingPad?) {
    Scaffold(containerColor = MaterialTheme.colorScheme.surfaceContainer, topBar = {
        Column {
            TopAppBar(title = {
                SpaceXTopAppBar(title = landingPad?.name ?: "", icon = R.drawable.location_pin_icon)
            }, navigationIcon = {
                SpaceXBackButton {}
            }, actions = {
                landingPad?.let { SpaceXInfoChip(text = it.status) }
            })
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
        }
    }) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
            when {
                landingPad == null -> LinearProgressIndicator()
                else -> Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        model = landingPad.images.large.first(),
                        contentDescription = null,
                        modifier = Modifier
                            .height(240.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        LandingPadBasicDetailsCard(landingPad)
                        LandingPadLandingStatsCard(landingPad)
                        LandingPadAboutCard(landingPad)
                        LandingPadLaunchHistoryCard(landingPad)
                        LandingPadLearnMoreCardCard(landingPad)
                    }
                }
            }
        }
    }
}

@Composable
fun LandingPadBasicDetailsCard(landingPad: LandingPad, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    SpaceXInfoCard(title = {
        Column {
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = landingPad.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700
                )

                SpaceXInfoChip(landingPad.status)
            }
            Text(landingPad.name, style = MaterialTheme.typography.labelMedium)
        }
    }, modifier = modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.surfaceContainer,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            SpaceXInfoChip(
                getTypeFullForms(landingPad.type),
                chipColor = getChipColorForType(landingPad.type)
            )

            Spacer(Modifier.height(16.dp))
            Row {
                Icon(painterResource(R.drawable.location_pin_icon), contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Location", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        landingPad.locality,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.W700
                    )
                    Text(landingPad.region, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(Modifier.height(16.dp))

            Row {
                Icon(painterResource(R.drawable.compas_icon), contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Coordinates", style = MaterialTheme.typography.labelMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "${landingPad.latitude}°, ${landingPad.longitude}°",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.W700
                    )
                    TextButton(onClick = {
                        openInGoogleMaps(
                            context,
                            landingPad.latitude,
                            landingPad.longitude
                        )
                    }) {
                        Icon(
                            painterResource(R.drawable.web_icon),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("View on Google Maps")
                    }
                }
            }
        }
    }
}

@Composable
fun LandingPadLandingStatsCard(landingPad: LandingPad, modifier: Modifier = Modifier) {
    SpaceXInfoCard(
        title = { Text("Landing Statistics", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) },
        icon = { Icon(painterResource(R.drawable.stats_icon), contentDescription = null, modifier = Modifier.size(18.dp)) },
        modifier = modifier
    ) {
        Column {
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp)
                    .weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "${landingPad.landingAttempts}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text("Total Attempts", style = MaterialTheme.typography.labelMedium)
                    }
                }
                Box(modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color(0x244CAF50))
                    .padding(8.dp)
                    .weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "${landingPad.landingSuccesses * 100 / landingPad.landingAttempts}",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFF4CAF50)
                        )
                        Text("Success Rate", style = MaterialTheme.typography.labelMedium, color = Color(0xFF4CAF50))
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceContainer)
            Spacer(Modifier.height(24.dp))
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.success_icon), contentDescription = null, tint = Color(0xFF4CAF50))
                    Text("Successful Landings", fontWeight = FontWeight.W700)
                    Spacer(Modifier.weight(1f))
                    Text("${landingPad.landingSuccesses}", fontWeight = FontWeight.W700)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.cancel_icon), contentDescription = null, tint = Color(0xFFC74747))
                    Text("Failed Landings", fontWeight = FontWeight.W700)
                    Spacer(Modifier.weight(1f))
                    Text("${landingPad.landingAttempts - landingPad.landingSuccesses}", fontWeight = FontWeight.W700)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.rocket_icon), contentDescription = null, tint = Color(
                        0xFF03A9F4
                    )
                    )
                    Text("Launches Supported", fontWeight = FontWeight.W700)
                    Spacer(Modifier.weight(1f))
                    Text("${landingPad.landingAttempts}", fontWeight = FontWeight.W700)
                }
            }
            Spacer(Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("Success Rate", style = MaterialTheme.typography.labelMedium)
                Text("${ landingPad.landingSuccesses * 100 / landingPad.landingAttempts }%", style = MaterialTheme.typography.labelMedium)
            }
            Spacer(Modifier.height(4.dp))
            LinearProgressIndicator(progress = { landingPad.landingSuccesses * 1f / landingPad.landingAttempts }, color = Color(0xFF4CAF50), modifier = Modifier.fillMaxWidth(), trackColor = MaterialTheme.colorScheme.surfaceContainer)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun LandingPadAboutCard(landingPad: LandingPad, modifier: Modifier = Modifier) {
    SpaceXInfoCard(
        title = { Text("About", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) },
        icon = { Icon(painterResource(R.drawable.info_icon), contentDescription = null) },
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))
        Text(landingPad.details, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Justify)
    }
}

@Composable
fun LandingPadLaunchHistoryCard(landingPad: LandingPad, modifier: Modifier = Modifier) {
    SpaceXInfoCard(
        title = { Text("Launch History", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) },
        icon = { Icon(painterResource(R.drawable.history_icon), contentDescription = null) },
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.clip(MaterialTheme.shapes.medium).background(MaterialTheme.colorScheme.surfaceContainer).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.rocket_icon), contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.Gray)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Total Launches", style = MaterialTheme.typography.titleMedium, color = Color.Gray)
                    Text("Missions supported by this pad", style = MaterialTheme.typography.labelMedium)
                }
                Spacer(Modifier.weight(1f))
                Text("${landingPad.launches.size}", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

@Composable
fun LandingPadLearnMoreCardCard(landingPad: LandingPad, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    SpaceXInfoCard(
        title = { Text("Learn More", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) },
        modifier = modifier
    ) {
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier.clip(MaterialTheme.shapes.medium).background(MaterialTheme.colorScheme.surfaceContainer).clickable(onClick = { openBrowserCustomTab(context, landingPad.wikipedia) }).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.web_icon), contentDescription = null, tint = Color.Gray)
                Spacer(Modifier.width(16.dp))
                Text("Wikipedia Article", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
                Icon(painterResource(R.drawable.open_link_icon), contentDescription = null, tint = Color.Gray)
            }
        }
    }
}


fun getTypeFullForms(type: String): String {
    return when (type.lowercase(getDefault())) {
        "rtls" -> "Return to Launch Site"
        "asds" -> "Autonomous Spaceport Drone Ship"
        else -> ""
    }
}