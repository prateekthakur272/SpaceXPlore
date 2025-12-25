package dev.prateekthakur.spacexplore.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dev.prateekthakur.spacexplore.presentation.screen.about.AboutSpaceXScreen
import dev.prateekthakur.spacexplore.presentation.screen.about.AboutSpaceXScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreen
import dev.prateekthakur.spacexplore.presentation.screen.capsule.CapsulesScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.cores.CoresScreen
import dev.prateekthakur.spacexplore.presentation.screen.cores.CoresScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.crew.CrewScreen
import dev.prateekthakur.spacexplore.presentation.screen.crew.CrewScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.dragon.DragonDetailsScreen
import dev.prateekthakur.spacexplore.presentation.screen.dragon.DragonDetailsScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.dragon.DragonsScreen
import dev.prateekthakur.spacexplore.presentation.screen.dragon.DragonsScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.history.EventHistoryScreen
import dev.prateekthakur.spacexplore.presentation.screen.history.EventHistoryScreenViewModel

@Composable
fun AppNavHost(startDestination: AppRoute = AppRoute.EventHistory) {

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

        composable<AppRoute.Crew> {
            val crewScreenViewModel = hiltViewModel<CrewScreenViewModel>()
            CrewScreen(crewScreenViewModel.state.collectAsState().value)
        }

        composable<AppRoute.Dragons> {
            val dragonsScreenViewModel = hiltViewModel<DragonsScreenViewModel>()
            DragonsScreen(dragonsScreenViewModel.state.collectAsState().value)
        }

        composable<AppRoute.DragonDetails> {
            val routeData = it.toRoute<AppRoute.DragonDetails>()
            val dragonDetailsScreenViewModel = hiltViewModel<DragonDetailsScreenViewModel>()
            dragonDetailsScreenViewModel.getDetails(routeData.id)
            DragonDetailsScreen(dragonDetailsScreenViewModel.state.collectAsState().value)
        }

        composable<AppRoute.EventHistory> {
            val eventHistoryScreenViewModel = hiltViewModel<EventHistoryScreenViewModel>()
            EventHistoryScreen(eventHistoryScreenViewModel.state.collectAsState().value)
        }
    }
}