package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Core
import dev.prateekthakur.spacexplore.domain.repository.CoreRepository
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi) : CoreRepository {
    override suspend fun getAll(): List<Core> {
        return spaceXApi.getCores()
    }

    override suspend fun getSingle(id: String): Core {
        return spaceXApi.getCore(id)
    }

    override suspend fun query(query: QueryRequest): List<Core> {
        return spaceXApi.getCores(query)
    }
}