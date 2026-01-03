package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Launch

interface LaunchesRepository {
    suspend fun getAll(): List<Launch>
    suspend fun getSingle(id: String): Launch
    suspend fun query(query: QueryRequest = QueryRequest()): List<Launch>
}