package dev.prateekthakur.spacexplore.presentation.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.EventHistory
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXFooterInfo
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.formatDate
import dev.prateekthakur.spacexplore.utils.formatTime
import dev.prateekthakur.spacexplore.utils.openBrowserCustomTab
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventHistoryScreen(events: List<EventHistory>?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Column {
                TopAppBar(title = {
                    SpaceXTopAppBar(title = "Event History", icon = R.drawable.history_icon)
                })
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            }
        }, bottomBar = {
            if(events != null){
                Column {
                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
                    BottomAppBar(containerColor = MaterialTheme.colorScheme.surface) {
                        Row(modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            SpaceXFooterInfo(title = "Total Events", value = "${events.size}", modifier = Modifier.weight(1f))
                            SpaceXFooterInfo(title = "First Event", value = events.last().eventDateUnixMillis.formatDate(), modifier = Modifier.weight(1f))
                            SpaceXFooterInfo(title = "Latest Event", value = events.first().eventDateUnixMillis.formatDate(), modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            when {
                events == null -> LinearProgressIndicator()
                events.isEmpty() -> Text("No Events Found")
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(events) { event ->
                        EventHistoryCard(eventHistory = event)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun EventHistoryCard(eventHistory: EventHistory, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp)
    ) {
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.padding()) {
            Box(
                modifier = Modifier
                    .width(2.dp)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(vertical = 8.dp)) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                )
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        SpaceXInfoCard(title = {
            Column {
                Text(eventHistory.eventDateUnixMillis.formatDate(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    eventHistory.eventDateUnixMillis.formatTime(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }, icon = {
            Icon(painterResource(R.drawable.calendar_icon), contentDescription = null)
        }, modifier = Modifier.padding(8.dp)) {
            Column(modifier = modifier) {
                Text(
                    eventHistory.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(eventHistory.details, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(24.dp))

                eventHistory.links.article?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { openBrowserCustomTab(context, it) }) { Text("Read Article") }
                }
            }
        }
    }
}
