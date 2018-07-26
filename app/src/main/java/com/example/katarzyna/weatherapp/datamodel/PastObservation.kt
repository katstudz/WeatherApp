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
        val timestamp: Double,
    val dateTimeISO: String,
    val tempC: Double,
    val tempF: Double,
    val dewpointC: Double,
    val dewpointF: Double,
    val humidity: Double,
    val pressureMB: Double,
    val pressureIN: Double,
    val spressureMB: Double,
    val spressureIN: Double,
    val altimeterMB: Double,
    val altimeterIN: Double,
    val windKTS: Double,
    val windKPH: Double,
    val windMPH: Double,
    val windSpeedKTS: Double,
    val windSpeedKPH: Double,
    val windSpeedMPH: Double,
    val windDirDEG: Double,
    val windDir: String,
    val windGustKTS: Double,
    val windGustKPH: Double,
    val windGustMPH: Double,
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
    val heatindexC: Double,
    val heatindexF: Double,
    val windchillC: Double,
    val windchillF: Double,
    val feelslikeC: Double,
    val feelslikeF: Double,
    val isDay: Boolean,
    val sunrise: Double,
    val sunriseISO: String,
    val sunset: Double,
    val sunsetISO: String,
    val snowDepthCM: Double,
    val snowDepthIN: Double,
    val precipMM: Double,
    val precipIN: Double,
    val solradWM2: Double,
    val solradMethod: String,
    val ceilingFT: Double,
    val ceilingM: Double,
    val light: Double,
    val QC: String,
    val QCcode: Double,
    val sky: Double
)