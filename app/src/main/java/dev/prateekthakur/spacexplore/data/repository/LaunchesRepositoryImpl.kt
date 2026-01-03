package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Launch
import dev.prateekthakur.spacexplore.domain.repository.LaunchesRepository
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi): LaunchesRepository {
    override suspend fun getAll(): List<Launch> {
        return spaceXApi.getLaunches()
    }

    override suspend fun getSingle(id: String): Launch {
        return spaceXApi.getLaunch(id)
    }

    override suspend fun query(query: QueryRequest): List<Launch> {
        return spaceXApi.getLaunches(query = query)
    }
}