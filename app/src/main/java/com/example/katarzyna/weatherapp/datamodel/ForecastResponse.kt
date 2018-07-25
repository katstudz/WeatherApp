package com.example.katarzyna.weatherapp.datamodel


data class ForecastResponse(
    val success: Boolean,
    val error: Any,
    val response: List<FrResponse>
)

data class FrResponse(
    val loc: Loc,
    val interval: String,
    val periods: List<Period>,
    val profile: Profile
)

data class Loc(
    val long: Double,
    val lat: Double
)


data class Period(
    val timestamp: Int,
    val validTime: String,
    val dateTimeISO: String,
    val avgTempC: Int,
    val maxTempF: Int,
    val minTempC: Int,
    val minTempF: Int,
    val humidity: Int
)