package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LandingPad(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("status") val status: String,
    @SerialName("type") val type: String,
    @SerialName("locality") val locality: String,
    @SerialName("region") val region: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("landing_attempts") val landingAttempts: Int,
    @SerialName("landing_successes") val landingSuccesses: Int,
    @SerialName("wikipedia") val wikipedia: String,
    @SerialName("details") val details: String,
    @SerialName("launches") val launches: List<String>,
    @SerialName("images") val images: LandingPadImages
)

@Serializable
data class LandingPadImages(
    @SerialName("large") val large: List<String>
)