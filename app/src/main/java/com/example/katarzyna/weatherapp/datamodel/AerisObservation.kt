package com.example.katarzyna.weatherapp.datamodel



data class AerisObservation(
    val success: Boolean,
    val error: Any,
    val response: Response
)

data class Response(
    val id: String,
    val loc: Loc,
    val place: Place,
    val profile: Profile,
    val obTimestamp: Int,
    val obDateTime: String,
    val ob: Ob,
    val raw: String,
    val relativeTo: RelativeTo
)

data class Loc(
    val long: Double,
    val lat: Double
)

data class RelativeTo(
    val lat: Double,
    val long: Double,
    val bearing: Int,
    val bearingENG: String,
    val distanceKM: Double,
    val distanceMI: Double
)

data class Profile(
    val tz: String,
    val elevM: Int,
    val elevFT: Int
)

data class Ob(
    val timestamp: Int,
    val dateTimeISO: String,
    val tempC: Int,
    val tempF: Int,
    val dewpointC: Int,
    val dewpointF: Int,
    val humidity: Int,
    val pressureMB: Int,
    val pressureIN: Double,
    val spressureMB: Int,
    val spressureIN: Double,
    val altimeterMB: Int,
    val altimeterIN: Double,
    val windKTS: Int,
    val windKPH: Int,
    val windMPH: Int,
    val windSpeedKTS: Int,
    val windSpeedKPH: Int,
    val windSpeedMPH: Int,
    val windDirDEG: Int,
    val windDir: String,
    val windGustKTS: Any,
    val windGustKPH: Any,
    val windGustMPH: Any,
    val flightRule: String,
    val visibilityKM: Double,
    val visibilityMI: Int,
    val weather: String,
    val weatherShort: String,
    val weatherCoded: String,
    val weatherPrimary: String,
    val weatherPrimaryCoded: String,
    val cloudsCoded: String,
    val icon: String,
    val heatindexC: Int,
    val heatindexF: Int,
    val windchillC: Int,
    val windchillF: Int,
    val feelslikeC: Int,
    val feelslikeF: Int,
    val isDay: Boolean,
    val sunrise: Int,
    val sunriseISO: String,
    val sunset: Int,
    val sunsetISO: String,
    val snowDepthCM: Any,
    val snowDepthIN: Any,
    val precipMM: Any,
    val precipIN: Any,
    val solradWM2: Int,
    val solradMethod: String,
    val ceilingFT: Any,
    val ceilingM: Any,
    val light: Int,
    val QC: String,
    val QCcode: Int,
    val sky: Int
)

data class Place(
    val name: String,
    val state: String,
    val country: String
)