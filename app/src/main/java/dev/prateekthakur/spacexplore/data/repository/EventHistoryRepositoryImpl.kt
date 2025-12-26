package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.EventHistory
import dev.prateekthakur.spacexplore.domain.repository.EventHistoryRepository
import javax.inject.Inject

class EventHistoryRepositoryImpl @Inject constructor(private val spaceXApi: SpaceXApi) : EventHistoryRepository {

    override suspend fun getAll(): List<EventHistory> {
        return spaceXApi.getEventHistory()
    }

    override suspend fun getSingle(id: String): EventHistory {
        return spaceXApi.getEventHistory(id)
    }

    override suspend fun query(query: QueryRequest): List<EventHistory> {
        return spaceXApi.getEventHistory(query)
    }
}