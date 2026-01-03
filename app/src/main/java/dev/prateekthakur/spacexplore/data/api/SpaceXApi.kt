package dev.prateekthakur.spacexplore.data.api

import dev.prateekthakur.spacexplore.data.request.QueryRequest
import dev.prateekthakur.spacexplore.domain.models.AboutSpaceX
import dev.prateekthakur.spacexplore.domain.models.Capsule
import dev.prateekthakur.spacexplore.domain.models.Core
import dev.prateekthakur.spacexplore.domain.models.Crew
import dev.prateekthakur.spacexplore.domain.models.Dragon
import dev.prateekthakur.spacexplore.domain.models.EventHistory
import dev.prateekthakur.spacexplore.domain.models.LandingPad
import dev.prateekthakur.spacexplore.domain.models.Launch
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SpaceXApi {

    @GET("/v4/company")
    suspend fun getCompanyInfo(): AboutSpaceX

    @GET("/v4/capsules")
    suspend fun getCapsules(): List<Capsule>

    @GET("/v4/capsules/{id}")
    suspend fun getCapsule(@Path("id") id: String): Capsule

    @POST("/v4/capsules/query")
    suspend fun getCapsules(@Body query: QueryRequest = QueryRequest()): List<Capsule>

    @GET("/v4/cores")
    suspend fun getCores(): List<Core>

    @GET("/v4/cores/{id}")
    suspend fun getCore(@Path("id") id: String): Core

    @POST("/v4/cores/query")
    suspend fun getCores(@Body query: QueryRequest = QueryRequest()): List<Core>

    @GET("/v4/crew")
    suspend fun getCrew(): List<Crew>

    @GET("/v4/crew/{id}")
    suspend fun getCrew(@Path("id") id: String): Crew

    @POST("/v4/crew/query")
    suspend fun getCrew(@Body query: QueryRequest = QueryRequest()): List<Crew>

    @GET("/v4/dragons")
    suspend fun getDragons(): List<Dragon>

    @GET("/v4/dragons/{id}")
    suspend fun getDragon(@Path("id") id: String): Dragon

    @POST("/v4/dragons/query")
    suspend fun getDragons(@Body query: QueryRequest = QueryRequest()): List<Dragon>

    @GET("/v4/history")
    suspend fun getEventHistory(): List<EventHistory>

    @GET("/v4/history/{id}")
    suspend fun getEventHistory(@Path("id") id: String): EventHistory

    @POST("/v4/history/query")
    suspend fun getEventHistory(query: QueryRequest = QueryRequest()): List<EventHistory>

    @GET("/v4/landpads")
    suspend fun getLandPads(): List<LandingPad>

    @GET("/v4/landpads/{id}")
    suspend fun getLandPad(@Path("id") id: String): LandingPad

    @POST("/v4/landpads/query")
    suspend fun getLandPads(@Body query: QueryRequest = QueryRequest()): List<LandingPad>

    @GET("/v5/launches")
    suspend fun getLaunches(): List<Launch>

    @GET("/v5/launches/{id}")
    suspend fun getLaunch(@Path("id") id: String): Launch

    @POST("/v5/launches/query")
    suspend fun getLaunches(@Body query: QueryRequest = QueryRequest()): List<Launch>
}