package dev.prateekthakur.spacexplore.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {
    @Serializable data object Capsule : AppRoute()
}
