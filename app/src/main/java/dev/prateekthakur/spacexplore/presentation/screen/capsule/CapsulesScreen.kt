package dev.prateekthakur.spacexplore.presentation.screen.capsule

import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.prateekthakur.spacexplore.domain.models.Capsule
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapsulesScreen(capsules: List<Capsule>?) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Capsules") },
                )
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceContainer)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {

            when {
                capsules == null -> LinearProgressIndicator()
                capsules.isEmpty() -> Text("No Capsules")
                else -> LazyColumn {
                    items(capsules) {
                        CapsuleTile(it)
                    }
                }
            }

        }
    }
}

@Composable
fun CapsuleTile(capsule: Capsule, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(capsule.serial, style = MaterialTheme.typography.titleMedium)
            capsule.lastUpdate?.let {
                Text(it, style = MaterialTheme.typography.labelLarge)
            }
            Spacer(Modifier.height(8.dp))
            Text(capsule.type, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {

                InfoChip("L/W - ${capsule.landLandings} / ${capsule.waterLandings}")
                InfoChip(capsule.type)
                if (capsule.status != "unknown") {
                    InfoChip(capsule.status.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            getDefault()
                        ) else it.toString()
                    })
                }
            }
        }
    }
}

@Composable
fun InfoChip(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.W600,
            color = textColor
        )
    }
}