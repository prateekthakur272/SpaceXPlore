package dev.prateekthakur.spacexplore.data.api

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX
import dev.prateekthakur.spacexplore.domain.models.Capsule
import dev.prateekthakur.spacexplore.domain.models.Core
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SpaceXApi {

    @GET("/v4/capsules")
    suspend fun getCapsules(): List<Capsule>

    @GET("/v4/capsules/{id}")
    suspend fun getCapsule(id: String): Capsule

    @POST("/v4/capsules/query")
    suspend fun getCapsules(@Body query: QueryRequest = QueryRequest()): List<Capsule>

    @GET("/v4/company")
    suspend fun getCompanyInfo(): AboutSpaceX

    @GET("/v4/cores")
    suspend fun getCores(): List<Core>

    @GET("/v4/cores/{id}")
    suspend fun getCore(id: String): Core

    @POST("/v4/cores/query")
    suspend fun getCores(@Body query: QueryRequest = QueryRequest()): List<Core>
}