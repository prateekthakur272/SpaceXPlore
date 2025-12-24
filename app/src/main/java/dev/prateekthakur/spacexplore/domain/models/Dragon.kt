package dev.prateekthakur.spacexplore.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dragon(
    @SerialName("heat_shield") val heatShield: HeatShield,
    @SerialName("launch_payload_mass") val launchPayloadMass: Mass,
    @SerialName("launch_payload_vol") val launchPayloadVolume: Volume,
    @SerialName("return_payload_mass") val returnPayloadMass: Mass,
    @SerialName("return_payload_vol") val returnPayloadVolume: Volume,
    @SerialName("pressurized_capsule") val pressurizedCapsule: PressurizedCapsule,
    @SerialName("trunk") val trunk: Trunk,
    @SerialName("height_w_trunk") val heightWithTrunk: Dimension,
    @SerialName("diameter") val diameter: Dimension,
    @SerialName("first_flight") val firstFlight: String,
    @SerialName("flickr_images") val flickrImages: List<String>,
    @SerialName("name") val name: String,
    @SerialName("type") val type: String,
    @SerialName("active") val active: Boolean,
    @SerialName("crew_capacity") val crewCapacity: Int,
    @SerialName("sidewall_angle_deg") val sidewallAngleDeg: Int,
    @SerialName("orbit_duration_yr") val orbitDurationYr: Int,
    @SerialName("dry_mass_kg") val dryMassKg: Int,
    @SerialName("dry_mass_lb") val dryMassLb: Int,
    @SerialName("thrusters") val thrusters: List<Thruster>,
    @SerialName("wikipedia") val wikipedia: String,
    @SerialName("description") val description: String,
    @SerialName("id") val id: String
)

@Serializable
data class HeatShield(
    @SerialName("material") val material: String,
    @SerialName("size_meters") val sizeMeters: Double,
    @SerialName("temp_degrees") val tempDegrees: Int,
    @SerialName("dev_partner") val devPartner: String
)

@Serializable
data class Mass(
    @SerialName("kg") val kg: Int,
    @SerialName("lb") val lb: Int
)

@Serializable
data class Volume(
    @SerialName("cubic_meters") val cubicMeters: Int,
    @SerialName("cubic_feet") val cubicFeet: Int
)

@Serializable
data class PressurizedCapsule(
    @SerialName("payload_volume") val payloadVolume: Volume
)

@Serializable
data class Trunk(
    @SerialName("trunk_volume") val trunkVolume: Volume,
    @SerialName("cargo") val cargo: Cargo
)

@Serializable
data class Cargo(
    @SerialName("solar_array") val solarArray: Int,
    @SerialName("unpressurized_cargo") val unpressurizedCargo: Boolean
)


@Serializable
data class Dimension(
    @SerialName("meters") val meters: Double, val feet: Double
)

@Serializable
data class Thruster(
    @SerialName("type") val type: String,
    @SerialName("amount") val amount: Int,
    @SerialName("pods") val pods: Int,
    @SerialName("fuel_1") val fuel1: String,
    @SerialName("fuel_2") val fuel2: String,
    @SerialName("isp") val isp: Int,
    @SerialName("thrust") val thrust: Thrust
)

@Serializable
data class Thrust(
    @SerialName("kN") val kiloNewton: Double,
    @SerialName("lbf") val lbf: Int
)
