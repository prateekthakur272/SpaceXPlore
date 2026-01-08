package dev.prateekthakur.spacexplore.presentation.screen.home

import dev.prateekthakur.spacexplore.R
import dev.prateekthakur.spacexplore.presentation.navigation.AppRoute

val HomeScreenOptions = listOf(
    HomeScreenTileOption(
        "Company Info",
        "SpaceX headquarters and company details",
        R.drawable.info_icon,
        AppRoute.AboutSpaceX
    ),
    HomeScreenTileOption(
        "Launches",
        "Complete history of SpaceX missions",
        R.drawable.rocket_icon,
        AppRoute.Launches
    ),
    HomeScreenTileOption(
        "Dragons",
        "Dragon details and statistics",
        R.drawable.flag_icon,
        AppRoute.Dragons
    ),
    HomeScreenTileOption(
        "Rocket Cores",
        "Reusable rocket core fleet details",
        R.drawable.people_icon,
        AppRoute.Cores
    ),
    HomeScreenTileOption(
        "Crew Members",
        "Astronauts and crew information",
        R.drawable.info_icon,
        AppRoute.Crew
    ),
    HomeScreenTileOption(
        "Capsules",
        "Dragon capsule fleet overview",
        R.drawable.info_icon,
        AppRoute.Capsule
    ),
    HomeScreenTileOption(
        "Event History",
        "Timeline of major SpaceX events",
        R.drawable.history_icon,
        AppRoute.EventHistory
    ),
    HomeScreenTileOption(
        "Landing Pads",
        "Landing facilities and statistics",
        R.drawable.flag_icon,
        AppRoute.LandingPads
    ),
)
