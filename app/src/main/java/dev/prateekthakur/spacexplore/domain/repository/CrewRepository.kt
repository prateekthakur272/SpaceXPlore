package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Crew

interface CrewRepository {
    suspend fun getAll(): List<Crew>
    suspend fun getSingle(id: String): Crew
    suspend fun query(query: QueryRequest): List<Crew>
}