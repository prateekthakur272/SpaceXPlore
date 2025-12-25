package dev.prateekthakur.spacexplore.presentation.screen.dragon

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.rememberCoroutineScope
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
import dev.prateekthakur.spacexplore.domain.models.Dragon
import dev.prateekthakur.spacexplore.domain.models.Thruster
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXBackButton
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoCard
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXInfoChip
import dev.prateekthakur.spacexplore.presentation.composables.SpaceXTopAppBar
import dev.prateekthakur.spacexplore.utils.openBrowserCustomTab
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragonDetailsScreen(dragon: Dragon?) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        SpaceXTopAppBar(
                            title = dragon?.name ?: "",
                            icon = R.drawable.rocket_icon
                        )
                    }, actions = {
                        dragon?.let {
                            if (it.active) {
                                SpaceXInfoChip("Active")
                            }
                        }
                    }, navigationIcon = {
                        SpaceXBackButton { }
                    }
                )
                HorizontalDivider()
            }
        }
    ) { paddingValues ->

        if (dragon != null) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues)) {
                SpaceXImageCarousel(dragon.flickrImages, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp))
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    SpaceXInfoCard(
                        title = { Text("About", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold) },
                        icon = { Icon(painter = painterResource(id = R.drawable.info_icon), contentDescription = null) }
                    ) {
                        Text(dragon.description, textAlign = TextAlign.Justify, style = MaterialTheme.typography.bodyMedium)
                    }

                    SpaceXInfoCard(title = {
                        Text("Basic Info", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }, icon = {
                        Icon(painter = painterResource(id = R.drawable.rocket_icon), contentDescription = null)
                    }) {
                        SpaceXInfoRow(title = "Type", value = dragon.type.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                getDefault()
                            ) else it.toString()
                        })
                        SpaceXInfoRow(title = "Crew Capacity", value = if(dragon.crewCapacity == 0) "Cargo Only" else "${dragon.crewCapacity} Members")
                        SpaceXInfoRow(title = "First Flight", value = dragon.firstFlight)
                        SpaceXInfoRow(title = "Orbit Duration", value = "${dragon.orbitDurationYr} Years")
                    }

                    SpaceXInfoCard(title = {
                        Text("Basic Info", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }, icon = {
                        Icon(painter = painterResource(id = R.drawable.ruler_icon), contentDescription = null)
                    }) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Height with trunk", value = "${dragon.heightWithTrunk.meters} Meters", subValue = "${dragon.heightWithTrunk.feet} Feet", modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Diameter", value = "${dragon.diameter.meters} Meters", subValue = "${dragon.diameter.feet} Feet", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Dry Mass", value = "${dragon.dryMassKg} kg", subValue = "${dragon.dryMassLb} lb", modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Sidewall Angle", value = "${dragon.sidewallAngleDeg}˚", modifier = Modifier.weight(1f))
                        }
                    }

                    SpaceXInfoCard(title = {
                        Text("Payload Capacity", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    }, icon = {
                        Icon(painter = painterResource(id = R.drawable.cube_icon), contentDescription = null)
                    }) {
                        Text("Launch Payload", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.W700)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Mass", value = "${dragon.launchPayloadMass.kg} kg", subValue = "${dragon.launchPayloadMass.lb}", modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Volume", value = "${dragon.launchPayloadVolume.cubicMeters} m³", subValue = "${dragon.launchPayloadVolume.cubicFeet} ft³", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Return Payload", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.W700)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Mass", value = "${dragon.returnPayloadMass.kg} kg", subValue = "${dragon.returnPayloadMass.lb}", modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Volume", value = "${dragon.returnPayloadVolume.cubicMeters} m³", subValue = "${dragon.returnPayloadVolume.cubicFeet} ft³", modifier = Modifier.weight(1f))
                        }
                    }

                    SpaceXInfoCard(
                        title = {
                            Text("Heat Shield", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }, icon = {
                            Icon(painter = painterResource(id = R.drawable.shield_icon), contentDescription = null)
                        }
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Material", value = dragon.heatShield.material, modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Size", value = "${dragon.heatShield.sizeMeters} Meters", modifier = Modifier.weight(1f))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            DragonDetailsInfoBlock(title = "Max Temp", value = "${dragon.heatShield.tempDegrees}˚C", modifier = Modifier.weight(1f))
                            DragonDetailsInfoBlock(title = "Dev Partner", value = "${dragon.heatShield.devPartner}˚", modifier = Modifier.weight(1f))
                        }
                    }

                    SpaceXInfoCard(
                        title = {
                            Text("Thrusters", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        }, icon = {
                            Icon(painter = painterResource(id = R.drawable.thruster_icon), contentDescription = null)
                        }
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            dragon.thrusters.forEach {
                                DragonThrusterCard(it)
                            }
                        }
                    }

                    Button(
                        onClick = { openBrowserCustomTab(context, dragon.wikipedia) },
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    ) {
                        Icon(painterResource(R.drawable.wikipedia_icon), contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Visit Wikipedia")
                    }
                }
            }
        } else {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                LinearProgressIndicator()
            }
        }
    }
}

@Composable
fun SpaceXImageCarousel(images: List<String>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState { images.size }
    Box(contentAlignment = Alignment.BottomCenter) {
        HorizontalPager(state = pagerState, modifier = modifier) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        SpaceXPagerIndicator(pagerState = pagerState, modifier = Modifier.padding(16.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpaceXPagerIndicator(pagerState: PagerState, modifier: Modifier = Modifier) {

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(pagerState.pageCount) { index ->
                val isSelected = pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .height(8.dp)
                        .width(if (isSelected) 16.dp else 8.dp)
                        .clip(CircleShape)
                        .clickable(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    index
                                )
                            }
                        })
                        .background(
                            if (isSelected)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                        )
                )
            }
        }
    }
}

@Composable
fun SpaceXInfoRow(title: String, value: String, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun DragonDetailsInfoBlock(title: String, value: String, modifier: Modifier = Modifier, subValue: String? = null) {
    Box(modifier = modifier
        .clip(MaterialTheme.shapes.medium)
        .background(MaterialTheme.colorScheme.surfaceContainer)
        .padding(16.dp)) {
        Column {
            Text(title, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.bodyLarge)
            Text(subValue?:"", style = MaterialTheme.typography.labelMedium)
        }
    }
}

@Composable
fun DragonThrusterCard(thruster: Thruster, modifier: Modifier = Modifier) {
    SpaceXCard {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(thruster.type, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.W700)
                SpaceXInfoChip("${thruster.amount} units", chipColor = Color(0xFF03A9F4))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Pods", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                    Text("${thruster.pods}")
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("ISP", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                    Text("${thruster.isp}")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Thrust", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                    Text("${thruster.thrust.kiloNewton} kN")
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Thrust(lb)", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
                    Text("${thruster.thrust.lbf}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(R.drawable.fuel_icon), contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Fuel", color = Color.Gray, style = MaterialTheme.typography.labelMedium)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(thruster.fuel1, style = MaterialTheme.typography.bodyMedium)
            Text(thruster.fuel2, style = MaterialTheme.typography.bodyMedium)
        }
    }
}