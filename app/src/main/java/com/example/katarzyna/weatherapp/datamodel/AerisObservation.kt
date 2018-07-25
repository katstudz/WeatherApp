package com.example.katarzyna.weatherapp.datamodel

data class AerisObservation(
    val success: Boolean,
    val error: Error,
    val response: Response
)

data class Error(
    val code: String,
    val description: String
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

data class Ob(
    val timestamp: Int,
    val dateTimeISO: String,
    val tempC: Double,
    val tempF: Int,
    val dewpointC: Double,
    val dewpointF: Int,
    val humidity: Int,
    val pressureMB: Int,
    val pressureIN: Double,
    val spressureMB: Int,
    val spressureIN: Double,
    val altimeterMB: Int,
    val altimeterIN: Double,
    val windKTS: Any,
    val windKPH: Any,
    val windMPH: Any,
    val windSpeedKTS: Any,
    val windSpeedKPH: Any,
    val windSpeedMPH: Any,
    val windDirDEG: Any,
    val windDir: Any,
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
    val precipMM: Double,
    val precipIN: Double,
    val solradWM2: Int,
    val solradMethod: String,
    val ceilingFT: Int,
    val ceilingM: Double,
    val light: Int,
    val QC: String,
    val QCcode: Int,
    val sky: Int
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

data class Place(
    val name: String,
    val state: String,
    val country: String
){
    fun getString():String{
        val firstCityName = name.split("/")[0]
        return "$firstCityName,$country"
    }
}
