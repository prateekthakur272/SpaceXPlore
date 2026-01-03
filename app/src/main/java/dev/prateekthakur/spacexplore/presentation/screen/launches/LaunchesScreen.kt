package dev.prateekthakur.spacexplore.presentation.screen.launches

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.Launch
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXFooterInfo
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.formatDate
import dev.prateekthakur.spacexplore.utils.formatTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchesScreen(launches: List<Launch>?) {
    Scaffold(containerColor = MaterialTheme.colorScheme.surfaceContainer, topBar = {
        Column {
            TopAppBar(title = {
                SpaceXTopAppBar(
                    title = "Launches",
                    icon = R.drawable.rocket_icon
                )
            })
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
        }
    }, bottomBar = {
        if(launches != null)
        Column {
            HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
                Row {
                    SpaceXFooterInfo(title = "Total Launches", value = "${launches.size}", modifier = Modifier.weight(1f))
                    SpaceXFooterInfo(title = "Failed Launches", value = "${launches.filter { it.failures.isNotEmpty() }.size}", modifier = Modifier.weight(1f))
                }
            }
        }
    }) { paddingValues ->
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            when{
                launches == null -> LinearProgressIndicator()
                launches.isEmpty() -> Text(text = "No Launches Found")
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(launches) {
                        LaunchCard(it, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun LaunchCard(launch: Launch, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    SpaceXCard(modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = onClick)) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(model = launch.links.patch?.small, modifier = Modifier.size(120.dp), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = launch.name, style = MaterialTheme.typography.titleLarge)
                        Text("Flight# ${launch.flightNumber}", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    if(launch.failures.isNotEmpty()){
                        SpaceXInfoChip("Failed", chipColor = Color(0xFFDA4F4F))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(R.drawable.calendar_icon), contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(launch.dateUnix.formatDate(), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.W700)
                        Text(launch.dateUnix.formatTime(), style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.W700, color = Color.Gray)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(8.dp))
                Text(launch.details ?: "", style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Justify, maxLines = 3, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}