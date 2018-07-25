package com.example.katarzyna.weatherapp.datamodel


data class PastObservation(
    val success: Boolean,
    val error: Any,
    val response: PastObservationResponse
)

data class PastObservationResponse(
        val id: String,
        val loc: Loc,
        val place: Place,
        val periods: List<PastObservationPeriods>,
        val profile: Profile
)

data class PastObservationPeriods(
    val ob: PastOb,
    val raw: String
)

data class PastOb(
    val timestamp: Int,
    val dateTimeISO: String,
    val tempC: Int,
    val tempF: Int,
    val dewpointC: Int,
    val dewpointF: Int,
    val humidity: Int,
    val pressureMB: Double,
    val pressureIN: Double,
    val spressureMB: Double,
    val spressureIN: Double,
    val altimeterMB: Double,
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
    val visibilityMI: Double,
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