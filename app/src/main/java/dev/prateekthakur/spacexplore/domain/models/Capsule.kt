package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Capsule(
    @SerialName("id") val id: String,
    @SerialName("reuse_count") val reuseCount: Int,
    @SerialName("water_landings") val waterLandings: Int,
    @SerialName("land_landings") val landLandings: Int,
    @SerialName("last_update") val lastUpdate: String?,
    @SerialName("launches") val launches: List<String>,
    @SerialName("serial") val serial: String,
    @SerialName("status") val status: String,
    @SerialName("type") val type: String,
)