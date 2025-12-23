package dev.prateekthakur.spacexplore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.prateekthakur.spacexplore.presentation.screen.about.AboutSpaceXScreen
import dev.prateekthakur.spacexplore.presentation.screen.about.AboutSpaceXScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreen
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.cores.CoresScreen
import dev.prateekthakur.spacexplore.presentation.screen.cores.CoresScreenViewModel

@Composable
fun AppNavHost(startDestination: AppRoute = AppRoute.Cores) {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = startDestination){

        composable<AppRoute.Capsule> {
            val capsuleScreenViewModel = hiltViewModel<CapsulesScreenViewModel>()
            CapsulesScreen(capsuleScreenViewModel.state.collectAsState().value)
        }

        composable<AppRoute.AboutSpaceX> {
            val aboutSpaceXScreenViewModel = hiltViewModel<AboutSpaceXScreenViewModel>()
            AboutSpaceXScreen(aboutSpaceXScreenViewModel.info.collectAsState().value)
        }

        composable<AppRoute.Cores> {
            val coresScreenViewModel = hiltViewModel<CoresScreenViewModel>()
            CoresScreen(coresScreenViewModel.state.collectAsState().value)
        }
    }
}