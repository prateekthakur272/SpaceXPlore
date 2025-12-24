package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Dragon

interface DragonRepository {
    suspend fun getAll(): List<Dragon>
    suspend fun getSingle(id: String): Dragon
    suspend fun query(query: QueryRequest): List<Dragon>
}