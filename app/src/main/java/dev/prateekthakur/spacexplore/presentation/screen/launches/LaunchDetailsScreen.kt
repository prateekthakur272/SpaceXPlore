package dev.prateekthakur.spacexplore.presentation.screen.launches

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.Failure
import dev.prateekthakur.spacexplore.domain.models.Fairings
import dev.prateekthakur.spacexplore.domain.models.Launch
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.formatDate
import dev.prateekthakur.spacexplore.utils.formatTime
import dev.prateekthakur.spacexplore.utils.openBrowserCustomTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailsScreen(launch: Launch?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Column {
                TopAppBar(title = {
                    SpaceXTopAppBar(
                        title = launch?.name ?: "",
                        icon = R.drawable.rocket_icon
                    )
                }, actions = {
                    if(launch?.success == false){
                        SpaceXInfoChip(text = "Failed", chipColor = MaterialTheme.colorScheme.error)
                    }
                })
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(), contentAlignment = Alignment.Center){
            when{
                launch == null -> LinearProgressIndicator()
                else -> Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())) {
                    Box(modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)){
                        AsyncImage(model = launch.links.patch?.small, contentDescription = null, modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp))
                    }

                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        LaunchBasicInfo(launch)

                        if(launch.details!=null){
                            LaunchMissionInfo(launch)
                        }

                        if(launch.failures.isNotEmpty()){
                            LaunchFailureDetails(launch.failures)
                        }

                        LaunchMissionStats(launch)

                        if(launch.fairings != null){
                            LaunchFairingsDetails(launch.fairings)
                        }

                        LaunchResources(launch)
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchBasicInfo(launch: Launch, modifier: Modifier = Modifier) {
    SpaceXInfoCard(title = {
        Row {
            Column {
                Text(launch.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700)
                Text("Flight# ${launch.flightNumber}", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
            }
            Spacer(modifier = Modifier.weight(1f))
            if(launch.success == false){
                SpaceXInfoChip("Failed", chipColor = MaterialTheme.colorScheme.error)
            }
        }
    }, modifier = modifier) {
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Icon(painterResource(R.drawable.calendar_icon), contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.padding(4.dp))
            Column {
                Text("Launch Date & Time", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Text(launch.dateUnix.formatDate(), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700)
                Text(launch.dateUnix.formatTime(), style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Icon(painterResource(R.drawable.thruster_icon), contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.padding(4.dp))
            Column {
                Text("Static Fire Test", style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                Text(launch.staticFireDateUnix?.formatDate() ?: "None", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700)
            }
        }
    }
}

@Composable
fun LaunchMissionInfo(launch: Launch, modifier: Modifier = Modifier) {
    SpaceXInfoCard(title = {
        Text("Mission Info", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700)
    }, modifier = modifier){
        Text(launch.details!!, style = MaterialTheme.typography.bodyMedium, textAlign = androidx.compose.ui.text.style.TextAlign.Justify)
    }
}

@Composable
fun LaunchFailureDetails(failures: List<Failure>, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.errorContainer)
        .border(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.error,
            shape = MaterialTheme.shapes.medium
        )){
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            Row {
                Icon(
                    painterResource(R.drawable.cancel_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    "Failure Analysis",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700,
                    color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            failures.forEach {
                Column {
                    Text(it.reason, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700, color = MaterialTheme.colorScheme.error)
                    Text("After ${it.time} Seconds, altitude ${it.altitude} km", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun LaunchMissionStats(launch: Launch, modifier: Modifier = Modifier) {

    @Composable
    fun LaunchStat(title: String, value: String, icon: Int, modifier: Modifier = Modifier) {
        Box(modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(16.dp)){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(icon), contentDescription = null, tint = Color.Gray)
                Spacer(modifier = Modifier.padding(4.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.labelLarge, color = Color.Gray)
                    Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700)
                }
            }
        }
    }

    SpaceXInfoCard(title = { Text("Mission Stats", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) }, modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LaunchStat(title = "Payloads", value = "${launch.payloads.size}", icon = R.drawable.cube_icon, modifier = Modifier.weight(1f))
            LaunchStat(title = "Crew", value = "${launch.crew.size}", icon = R.drawable.people_icon, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            LaunchStat(title = "Ships", value = "${launch.ships.size}", icon = R.drawable.ship_icon, modifier = Modifier.weight(1f))
            LaunchStat(title = "Cores", value = "${launch.cores.size}", icon = R.drawable.rocket_icon, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun LaunchFairingsDetails(fairings: Fairings, modifier: Modifier = Modifier) {

    @Composable
    fun FairingDetailRow(title: String, value: Boolean               ) {
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700)
            Spacer(modifier = Modifier.weight(1f))
            Icon(painterResource(if(fairings.reused == true) R.drawable.success_icon else R.drawable.cancel_icon), contentDescription = null, modifier = Modifier.size(16.dp))
        }
    }

    SpaceXInfoCard(title = { Text("Fairings", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) }, modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FairingDetailRow("Reused", fairings.reused == true)
            FairingDetailRow("Recovery Attempt", fairings.recoveryAttempt == true)
            FairingDetailRow("Recovered", fairings.recovered == true)
        }
    }
}

@Composable
fun LaunchResources(launch: Launch, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    @Composable
    fun ResourceItem(title: String, icon: Int, onClick: () -> Unit) {
        ListItem(
            headlineContent = { Text(title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700) },
            leadingContent = { Icon(painterResource(icon), contentDescription = null) },
            trailingContent = { Icon(painterResource(R.drawable.open_link_icon), contentDescription = null) },
            modifier = Modifier.clickable { onClick() }
        )
    }

    SpaceXInfoCard(title = { Text("Resources", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700) }, modifier = modifier) {
        launch.links.webcast?.let{
            ResourceItem("Watch Webcast", R.drawable.youtube_icon) { openBrowserCustomTab(context, it) }
        }
        launch.links.article?.let{
            ResourceItem("Read Article", R.drawable.article_icon) { openBrowserCustomTab(context, it) }
        }
        launch.links.wikipedia?.let {
            ResourceItem("Wikipedia", R.drawable.wikipedia_icon) { openBrowserCustomTab(context, it) }
        }
    }
}