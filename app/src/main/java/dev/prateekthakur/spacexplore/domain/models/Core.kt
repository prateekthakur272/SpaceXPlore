package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Core (
    @SerialName("block") val block: Int?,
    @SerialName("reuse_count") val reuseCount: Int,
    @SerialName("rtls_attempts") val rtlsAttempts: Int,
    @SerialName("rtls_landings") val rtlsLandings: Int,
    @SerialName("asds_attempts") val asdsAttempts: Int,
    @SerialName("asds_landings") val asdsLandings: Int,
    @SerialName("last_update") val lastUpdate: String?,
    @SerialName("launches") val launches: List<String>,
    @SerialName("serial") val serial: String,
    @SerialName("status") val status: String,
    @SerialName("id") val id: String,
)
