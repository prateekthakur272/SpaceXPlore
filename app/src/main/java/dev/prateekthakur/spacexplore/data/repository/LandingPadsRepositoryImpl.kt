package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.domain.repository.LandingPadsRepository
import javax.inject.Inject

class LandingPadsRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi) : LandingPadsRepository {
    override suspend fun getAll(): List<LandingPad> {
        return spaceXApi.getLandPads()
    }

    override suspend fun getSingle(id: String): LandingPad {
        return spaceXApi.getLandPad(id)
    }

    override suspend fun query(query: QueryRequest): List<LandingPad> {
        return spaceXApi.getLandPads(query)
    }
}