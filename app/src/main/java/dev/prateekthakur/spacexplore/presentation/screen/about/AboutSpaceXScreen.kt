package dev.prateekthakur.spacexplore.presentation.screen.about

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX
import dev.prateekthakur.spacexplore.utils.openExternalLink

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AboutSpaceXScreen(info: AboutSpaceX?) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        info?.let { info ->
            Column(
                modifier = Modifier.verticalScroll(state = scrollState)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Image(
                        painter = painterResource(R.drawable.spacex_image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.surface.copy(0.9f)
                                    )
                                )
                            )
                    )

                    Text(
                        text = info.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    InfoCard {
                        Text(
                            info.summary,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Justify
                        )
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        InfoCard(modifier = Modifier.weight(1f)) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painterResource(R.drawable.calendar_icon),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "Founded",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.W700
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    "${info.founded} (by ${info.founder})",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        InfoCard(modifier = Modifier.weight(1f)) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painterResource(R.drawable.people_icon),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "Employees",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.W700
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    "${info.employees}", style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    InfoCard {
                        Column {
                            Text(
                                "Leadership",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.W700
                            )
                            Spacer(Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("CEO", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    info.ceo,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("CTO", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    info.cto,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("COO", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    info.coo,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("CTO Propulsion", style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    info.ctoPropulsion,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.W700
                                )
                            }
                        }
                    }

                    Row(modifier = Modifier.height(IntrinsicSize.Max)) {
                        InfoCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painterResource(R.drawable.dollar_icon),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "Valuation",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.W700
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    "${info.valuation}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        Spacer(Modifier.width(16.dp))

                        InfoCard(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Column {
                                Row {
                                    Icon(
                                        painterResource(R.drawable.location_pin_icon),
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(8.dp))
                                    Text(
                                        "Headquarters",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.W700
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    "${info.headquarters.address}, ${info.headquarters.city}, ${info.headquarters.state}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    InfoCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row {
                                Icon(
                                    painterResource(R.drawable.rocket_icon),
                                    contentDescription = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Operations",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Spacer(Modifier.height(16.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        "Vehicles", style = MaterialTheme.typography.bodyMedium,

                                        )
                                    Text(
                                        "${info.vehicles}",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        "Launch Sites", style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        "${info.launchSites}",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }

                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        "Test Sites", style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        "${info.testSites}",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }
                        }
                    }

                    InfoCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row {
                                Icon(
                                    painterResource(R.drawable.link_icon), contentDescription = null
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    "Links",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.W700
                                )
                            }
                            Spacer(Modifier.height(16.dp))

                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    openExternalLink(
                                        context,
                                        info.links.website
                                    )
                                }),
                                headlineContent = {
                                    Text(
                                        "Website",
                                        fontWeight = FontWeight.W700
                                    )
                                },
                                leadingContent = {
                                    Icon(
                                        painterResource(R.drawable.web_icon),
                                        contentDescription = null
                                    )
                                },
                                trailingContent = {
                                    Icon(
                                        painterResource(R.drawable.open_link_icon),
                                        contentDescription = null
                                    )
                                })

                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    openExternalLink(
                                        context,
                                        info.links.twitter
                                    )
                                }),
                                headlineContent = {
                                    Text(
                                        "Twitter",
                                        fontWeight = FontWeight.W700
                                    )
                                },
                                leadingContent = {
                                    Icon(
                                        painterResource(R.drawable.twitter_icon),
                                        contentDescription = null
                                    )
                                },
                                trailingContent = {
                                    Icon(
                                        painterResource(R.drawable.open_link_icon),
                                        contentDescription = null
                                    )
                                })

                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    openExternalLink(
                                        context,
                                        info.links.flickr
                                    )
                                }),
                                headlineContent = {
                                    Text(
                                        "Flickr",
                                        fontWeight = FontWeight.W700
                                    )
                                },
                                leadingContent = {
                                    Icon(
                                        painterResource(R.drawable.flickr_icon),
                                        contentDescription = null
                                    )
                                },
                                trailingContent = {
                                    Icon(
                                        painterResource(R.drawable.open_link_icon),
                                        contentDescription = null
                                    )
                                })

                            ListItem(
                                modifier = Modifier.clickable(onClick = {
                                    openExternalLink(context, info.links.elonTwitter)
                                }),
                                headlineContent = {
                                    Text(
                                        "Elon Musk Twitter", fontWeight = FontWeight.W700
                                    )
                                }, leadingContent = {
                                    Icon(
                                        painterResource(R.drawable.twitter_icon),
                                        contentDescription = null
                                    )
                                }, trailingContent = {
                                    Icon(
                                        painterResource(R.drawable.open_link_icon),
                                        contentDescription = null
                                    )
                                })
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun InfoCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                shape = MaterialTheme.shapes.large
            )
            .padding(16.dp)
    ) {
        content()
    }
}
