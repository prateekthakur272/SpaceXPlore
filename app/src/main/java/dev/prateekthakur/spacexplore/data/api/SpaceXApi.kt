package dev.prateekthakur.spacexplore.data.api

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.Capsule
import retrofit2.http.Body
import retrofit2.http.GET

interface SpaceXApi {

    @GET("/v4/capsules")
    suspend fun getCapsules(): List<Capsule>

    @GET("/v4/capsules/{id}")
    suspend fun getCapsule(id: String): Capsule

    @GET("/v4/capsules/query")
    suspend fun getCapsules(@Body query: QueryRequest = QueryRequest()): List<Capsule>
}