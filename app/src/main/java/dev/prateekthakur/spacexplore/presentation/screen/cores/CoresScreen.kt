package dev.prateekthakur.spacexplore.presentation.screen.cores

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.Core

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoresScreen(cores: List<Core>?) {

    val statusOptions = cores?.map { it.status }?.distinct()?.sorted()
    var selectedFilter by rememberSaveable { mutableStateOf<String?>(null) }

    val cores = cores?.filter { it.status == selectedFilter || selectedFilter == null }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer, topBar = {
            Column {
                TopAppBar(
                    title = {
                        Column {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.rocket_icon),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    "Rocket Cores",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.W700
                                )
                            }

                            statusOptions?.let { options ->
                                FlowRow(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    FilterChip(
                                        selected = selectedFilter == null,
                                        onClick = { selectedFilter = null },
                                        label = { Text("All") })
                                    options.forEach { option ->
                                        FilterChip(
                                            selected = selectedFilter == option,
                                            onClick = { selectedFilter = option },
                                            label = { Text(option) })
                                    }
                                }
                            }
                        }
                    })
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            }
        }) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when {
                cores == null -> LinearProgressIndicator()
                cores.isEmpty() -> Text(text = "No cores found")
                else -> CoresListView(cores)
            }
        }
    }
}

@Composable
fun CoresListView(cores: List<Core>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(cores) {
            CoreCard(it, modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp))
        }
    }
}

@Composable
fun CoreCard(core: Core, modifier: Modifier = Modifier) {

    val chipColor = when (core.status) {
        "active" -> Color(0xFF4CAF50)
        "expended" -> Color(0xFF2196F3)
        "lost" -> Color(0xFFDA5959)
        "inactive" -> Color(0xFFEE9D4A)
        else -> Color(0xFFABABAB)
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.border(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.surfaceDim,
            shape = MaterialTheme.shapes.medium
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    core.serial,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700
                )

                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(chipColor)
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        core.status,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Box(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(R.drawable.pulse_icon),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                "Reuse Count",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text("${core.reuseCount}")
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painterResource(R.drawable.rocket_icon),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                "Launches",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge
                            )
                            Text("${core.reuseCount}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp)
            ) {
                Column {
                    Text(
                        "Landing Statistics",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "RTLS",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.W700
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${core.rtlsLandings}/${core.rtlsAttempts}")
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                "ASDS",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.W700
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${core.asdsLandings}/${core.asdsAttempts}")
                        }
                    }
                }
            }

            core.lastUpdate?.let {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceContainer)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Last Update",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray,
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(core.lastUpdate, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
