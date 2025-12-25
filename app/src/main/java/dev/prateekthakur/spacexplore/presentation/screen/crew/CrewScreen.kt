package dev.prateekthakur.spacexplore.presentation.screen.crew

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.Crew
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.openBrowserCustomTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrewScreen(crew: List<Crew>?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer, topBar = {
            Column {
                TopAppBar(title = {
                    SpaceXTopAppBar(title = "Crew", icon = R.drawable.people_icon)
                })
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            }
        }) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                crew == null -> LinearProgressIndicator()
                crew.isEmpty() -> Text(text = "No crews found")
                else -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(crew) {
                        CrewCard(
                            it, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CrewCard(crew: Crew, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    SpaceXCard(modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row {
                AsyncImage(
                    model = crew.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        crew.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W700
                    )

                    Text(
                        crew.agency, color = Color.Gray, style = MaterialTheme.typography.titleSmall
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    SpaceXInfoChip(crew.status)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painterResource(R.drawable.rocket_icon),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            "Total Launces",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.W700,
                            color = Color.Gray
                        )
                        Text("${crew.launches.size}", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .clickable(onClick = { openBrowserCustomTab(context, crew.wikipedia) })
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(R.drawable.wikipedia_icon), contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Wikipedia", style = MaterialTheme.typography.labelLarge)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(painterResource(R.drawable.open_link_icon), contentDescription = null)
                }

            }
        }
    }
}
