package dev.prateekthakur.spacexplore.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightScheme = lightColorScheme(
    primary = Color(0xFF2196F3),
    secondary = Color(0xFF009688),
    tertiary = Color(0xFFFFC107),
    onPrimary = Color(0xFFFFFFFF)
)

private val darkScheme = darkColorScheme(
    primary = Color(0xFF004D88),
    secondary = Color(0xFF006257),
    tertiary = Color(0xFF8D6B00),
    onPrimary = Color(0xFFFFFFFF)
)


@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = AppTypography, content = content
    )
}

