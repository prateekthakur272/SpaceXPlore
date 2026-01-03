package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Launch(
    @SerialName("fairings") val fairings: Fairings? = null,
    @SerialName("links") val links: LaunchLinks,
    @SerialName("static_fire_date_utc") val staticFireDateUtc: String? = null,
    @SerialName("static_fire_date_unix") val staticFireDateUnix: Long? = null,
    @SerialName("net") val net: Boolean,
    @SerialName("window") val window: Int?,
    @SerialName("rocket") val rocket: String,
    @SerialName("success") val success: Boolean? = null,
    @SerialName("failures") val failures: List<Failure> = emptyList(),
    @SerialName("details") val details: String? = null,
    @SerialName("crew") val crew: List<LaunchCrew> = emptyList(),
    @SerialName("ships") val ships: List<String> = emptyList(),
    @SerialName("capsules") val capsules: List<String> = emptyList(),
    @SerialName("payloads") val payloads: List<String>,
    @SerialName("launchpad") val launchpad: String,
    @SerialName("flight_number") val flightNumber: Int,
    @SerialName("name") val name: String,
    @SerialName("date_utc") val dateUtc: String,
    @SerialName("date_unix") val dateUnix: Long,
    @SerialName("date_local") val dateLocal: String,
    @SerialName("date_precision") val datePrecision: String,
    @SerialName("upcoming") val upcoming: Boolean,
    @SerialName("cores") val cores: List<LaunchCore>,
    @SerialName("auto_update") val autoUpdate: Boolean,
    @SerialName("tbd") val tbd: Boolean,
    @SerialName("launch_library_id") val launchLibraryId: String? = null,
    @SerialName("id") val id: String
)

@Serializable
data class Fairings(
    @SerialName("reused") val reused: Boolean? = null,
    @SerialName("recovery_attempt") val recoveryAttempt: Boolean? = null,
    @SerialName("recovered") val recovered: Boolean? = null,
    @SerialName("ships") val ships: List<String> = emptyList()
)

@Serializable
data class LaunchLinks(
    @SerialName("patch") val patch: Patch? = null,
    @SerialName("reddit") val reddit: Reddit? = null,
    @SerialName("flickr") val flickr: Flickr,
    @SerialName("presskit") val presskit: String? = null,
    @SerialName("webcast") val webcast: String? = null,
    @SerialName("youtube_id") val youtubeId: String? = null,
    @SerialName("article") val article: String? = null,
    @SerialName("wikipedia") val wikipedia: String? = null
)

@Serializable
data class Patch(
    @SerialName("small") val small: String? = null,
    @SerialName("large") val large: String? = null
)

@Serializable
data class Reddit(
    @SerialName("campaign") val campaign: String? = null,
    @SerialName("launch") val launch: String? = null,
    @SerialName("media") val media: String? = null,
    @SerialName("recovery") val recovery: String? = null
)

@Serializable
data class Flickr(
    @SerialName("small") val small: List<String> = emptyList(),
    @SerialName("original") val original: List<String> = emptyList()
)

@Serializable
data class Failure(
    @SerialName("time") val time: Int,
    @SerialName("altitude") val altitude: Int? = null,
    @SerialName("reason") val reason: String
)

@Serializable
data class LaunchCore(
    @SerialName("core") val coreId: String? = null,
    @SerialName("flight") val flight: Int? = null,
    @SerialName("gridfins") val gridfins: Boolean? = null,
    @SerialName("legs") val legs: Boolean? = null,
    @SerialName("reused") val reused: Boolean? = null,
    @SerialName("landing_attempt") val landingAttempt: Boolean? = null,
    @SerialName("landing_success") val landingSuccess: Boolean? = null,
    @SerialName("landing_type") val landingType: String? = null,
    @SerialName("landpad") val landpad: String? = null
)

@Serializable
data class LaunchCrew(
    @SerialName("crew") val crewId: String,
    @SerialName("role") val role: String
)