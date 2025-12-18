package dev.prateekthakur.spacexplore.data.repository

import dev.prateekthakur.spacexplore.data.api.SpaceXApi
import dev.prateekthakur.spacexplore.data.request.FieldCondition
import dev.prateekthakur.spacexplore.data.request.Query
import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Capsule
import dev.prateekthakur.spacexplore.domain.repository.CapsuleRepository
import javax.inject.Inject

class CapsuleRepositoryImpl @Inject constructor(
    private val spaceXApi: SpaceXApi
) : CapsuleRepository {

    override suspend fun getAll(): List<Capsule> {
        return spaceXApi.getCapsules()
    }

    override suspend fun getSingle(id: String): Capsule {
        return spaceXApi.getCapsule(id)
    }

    override suspend fun query(serial: String, status: String, type: String): List<Capsule> {
        val query = QueryRequest(
            query = Query(
                and = listOf(
                    Query(fields = mapOf("serial" to FieldCondition(eq = serial))),
                    Query(fields = mapOf("status" to FieldCondition(eq = status))),
                    Query(fields = mapOf("type" to FieldCondition(eq = type)))
                )
            )
        )
        return spaceXApi.getCapsules(query)
    }

}