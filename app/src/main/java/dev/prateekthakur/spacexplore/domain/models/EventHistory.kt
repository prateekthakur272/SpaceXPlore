package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EventHistory(
    @SerialName("title") val title: String,
    @SerialName("event_date_utc") val eventDateUtc: String,
    @SerialName("event_date_unix") val eventDateUnix: Long,
    @SerialName("details") val details: String,
    @SerialName("links") val links: HistoryLinks
)

@Serializable
data class HistoryLinks(
    @SerialName("article") val article: String? = null
)