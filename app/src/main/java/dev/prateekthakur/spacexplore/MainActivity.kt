package dev.prateekthakur.spacexplore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreen
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreenViewModel
import dev.prateekthakur.spacexplore.presentation.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val capsulesScreenViewModel = hiltViewModel<CapsulesScreenViewModel>()
                CapsulesScreen(capsulesScreenViewModel.state.collectAsState().value)
            }
        }
    }
}
