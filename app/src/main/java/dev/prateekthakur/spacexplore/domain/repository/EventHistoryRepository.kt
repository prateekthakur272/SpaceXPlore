package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.EventHistory

interface EventHistoryRepository {
    suspend fun getAll(): List<EventHistory>
    suspend fun getSingle(id: String): EventHistory
    suspend fun query(query: QueryRequest = QueryRequest()): List<EventHistory>
}