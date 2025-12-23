package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Crew(
    @SerialName("name") val name: String,
    @SerialName("agency") val agency: String,
    @SerialName("image") val image: String,
    @SerialName("wikipedia") val wikipedia: String,
    @SerialName("launches") val launches: List<String>,
    @SerialName("status") val status: String,
    @SerialName("id") val id: String
)
