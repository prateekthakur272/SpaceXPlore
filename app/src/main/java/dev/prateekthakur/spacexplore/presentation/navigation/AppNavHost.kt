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
import dev.prateekthakur.spacexplore.presentation.screen.home.HomeScreen
import dev.prateekthakur.spacexplore.presentation.screen.landingPads.LandingPadDetailScreen
import dev.prateekthakur.spacexplore.presentation.screen.landingPads.LandingPadDetailScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.landingPads.LandingPadsScreen
import dev.prateekthakur.spacexplore.presentation.screen.landingPads.LandingPadsScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.launches.LaunchDetailsScreen
import dev.prateekthakur.spacexplore.presentation.screen.launches.LaunchDetailsScreenViewModel
import dev.prateekthakur.spacexplore.presentation.screen.launches.LaunchesScreen
import dev.prateekthakur.spacexplore.presentation.screen.launches.LaunchesScreenViewModel

@Composable
fun AppNavHost(startDestination: AppRoute = AppRoute.Home) {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = startDestination){

        composable <AppRoute.Home>{
            HomeScreen{navHostController.navigate(it)}
        }

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
            DragonsScreen(dragonsScreenViewModel.state.collectAsState().value){
                navHostController.navigate(AppRoute.DragonDetails(it.id))
            }
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

        composable<AppRoute.LandingPads> {
            val landingPadsScreenViewModel = hiltViewModel<LandingPadsScreenViewModel>()
            LandingPadsScreen(landingPadsScreenViewModel.state.collectAsState().value){
                navHostController.navigate(AppRoute.LandingPadDetail(it.id))
            }
        }

        composable<AppRoute.LandingPadDetail> {
            val routeData = it.toRoute<AppRoute.LandingPadDetail>()
            val landingPadDetailsScreenViewModel = hiltViewModel<LandingPadDetailScreenViewModel>()
            landingPadDetailsScreenViewModel.loadDetails(routeData.id)
            LandingPadDetailScreen(landingPadDetailsScreenViewModel.state.collectAsState().value)
        }

        composable<AppRoute.Launches> {
            val launchesScreenViewModel = hiltViewModel<LaunchesScreenViewModel>()
            LaunchesScreen(launchesScreenViewModel.state.collectAsState().value){
                navHostController.navigate(AppRoute.LaunchDetails(it.id))
            }
        }

        composable<AppRoute.LaunchDetails> {
            val routeData = it.toRoute<AppRoute.LaunchDetails>()
            val launchDetailsScreenViewModel = hiltViewModel<LaunchDetailsScreenViewModel>()
            launchDetailsScreenViewModel.loadData(routeData.id)
            LaunchDetailsScreen(launchDetailsScreenViewModel.state.collectAsState().value)
        }
    }
}