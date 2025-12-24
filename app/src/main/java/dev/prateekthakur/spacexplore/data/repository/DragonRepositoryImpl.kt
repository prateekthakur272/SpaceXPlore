package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Dragon
import dev.prateekthakur.spacexplore.domain.repository.DragonRepository
import javax.inject.Inject

class DragonRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi): DragonRepository {
    override suspend fun getAll(): List<Dragon> {
        return spaceXApi.getDragons()
    }

    override suspend fun getSingle(id: String): Dragon {
        return spaceXApi.getDragon(id)
    }

    override suspend fun query(query: QueryRequest): List<Dragon> {
        return spaceXApi.getDragons(query)
    }
}