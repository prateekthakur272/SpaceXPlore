package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Crew
import dev.prateekthakur.spacexplore.domain.repository.CrewRepository
import javax.inject.Inject

class CrewRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi) : CrewRepository {
    override suspend fun getAll(): List<Crew> {
        return spaceXApi.getCrew()
    }

    override suspend fun getSingle(id: String): Crew {
        return spaceXApi.getCrew(id)
    }

    override suspend fun query(query: QueryRequest): List<Crew> {
        return spaceXApi.getCrew(query)
    }
}