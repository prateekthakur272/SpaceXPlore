package dev.prateekthakur.spacexplore.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable data object Capsule : AppRoute()
    @Serializable data object AboutSpaceX : AppRoute()
    @Serializable data object Cores : AppRoute()
    @Serializable data object Crew : AppRoute()
    @Serializable data object Dragons : AppRoute()
    @Serializable data class DragonDetails(val id: String) : AppRoute()
}
