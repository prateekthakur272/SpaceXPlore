package dev.prateekthakur.spacexplore.data.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QueryRequest(
    @SerialName("query") val query: Query = Query(),
    @SerialName("options") val options: QueryOptions = QueryOptions()
)

@Serializable
data class Query(
    @SerialName($$"$and") val and: List<Query>? = null,
    @SerialName($$"$or") val or: List<Query>? = null,
    @SerialName("fields") val fields: Map<String, FieldCondition>? = null
)

@Serializable
data class FieldCondition(
    @SerialName($$"$eq") val eq: String? = null,
    @SerialName($$"$gt") val gt: String? = null,
    @SerialName($$"$gte") val gte: String? = null,
    @SerialName($$"$lt") val lt: String? = null,
    @SerialName($$"$lte") val lte: String? = null,
    @SerialName($$"$in") val `in`: List<String>? = null,
    @SerialName($$"$regex") val regex: String? = null
)

@Serializable
data class QueryOptions(
    val limit: Int? = null,
    val skip: Int? = null,
    val sort: Map<String, Int>? = null
)
