package dev.prateekthakur.spacexplore.domain.repository

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.LandingPad

interface LandingPadsRepository {
    suspend fun getAll(): List<LandingPad>
    suspend fun getSingle(id: String): LandingPad
    suspend fun query(query: QueryRequest = QueryRequest()): List<LandingPad>
}