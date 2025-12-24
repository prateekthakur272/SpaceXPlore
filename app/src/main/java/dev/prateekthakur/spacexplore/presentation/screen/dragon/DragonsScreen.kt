package dev.prateekthakur.spacexplore.presentation.screen.dragon

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.Dragon
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragonsScreen(dragons: List<Dragon>?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Column {
                TopAppBar(title = {
                    SpaceXTopAppBar(title = "Dragon Capsules", icon = R.drawable.rocket_icon)
                })
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceDim)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                dragons == null -> LinearProgressIndicator()
                dragons.isEmpty() -> Text("No Dragons Found")
                else -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(dragons) { dragon ->
                            DragonCard(
                                dragon = dragon,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DragonCard(dragon: Dragon, modifier: Modifier = Modifier) {
    SpaceXCard(modifier = modifier.fillMaxWidth()) {
        Column {
            SubcomposeAsyncImage(
                model = dragon.flickrImages.first(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is AsyncImagePainter.State.Error -> {
                        Image(
                            painter = painterResource(R.drawable.spacex_image),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    else -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
        }

        Column(modifier = Modifier.padding(16.dp)) {

            Text(dragon.name, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(dragon.type, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                dragon.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                DragonCardInfoBlock(title = "Crew", value = "${dragon.crewCapacity}", icon = R.drawable.people_icon, modifier = Modifier.weight(1f))
                DragonCardInfoBlock(title = "Mass", value = "${dragon.crewCapacity}", icon = R.drawable.weight_icon, modifier = Modifier.weight(1f))
                DragonCardInfoBlock(title = "Height", value = "${dragon.crewCapacity}", icon = R.drawable.ruler_icon, modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun DragonCardInfoBlock(title: String, value: String, icon: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(24.dp, 16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(title, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.W700)
        }
    }
}