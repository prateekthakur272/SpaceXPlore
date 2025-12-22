package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX

interface AboutSpaceXRepository {
    suspend fun getInfo() : AboutSpaceX
}