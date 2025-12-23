package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Core

interface CoreRepository {
    suspend fun getAll(): List<Core>
    suspend fun getSingle(id: String): Core
    suspend fun query(query: QueryRequest): List<Core>
}
