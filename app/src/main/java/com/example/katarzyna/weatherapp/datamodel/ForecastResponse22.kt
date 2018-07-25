package com.example.katarzyna.weatherapp.datamodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.concurrent.TimeUnit

data class ForecastResponse22(@Expose @SerializedName("city") var city: City?,
                              @Expose @SerializedName("cnt") var cnt: Long?,
                              @Expose @SerializedName("list") var forecastList: List<Forecast> = ArrayList())



data class Forecast(@Expose @SerializedName("dt") var dt: Long?,
                    @Expose @SerializedName("main") var main: Main?,
                    @Expose @SerializedName("weather") var weatherList: List<Weather> = ArrayList(),
                    @Expose @SerializedName("clouds") var clouds: Clouds?,
                    @Expose @SerializedName("wind") var wind: Wind?,
                    @Expose @SerializedName("rain") var rain: Rain?,
                    @Expose @SerializedName("snow") var snow: Snow?,
                    @Expose @SerializedName("dt_txt") var dateString: String?) {

    fun dtStartMillis(): Long = dt!!.times(1000)
    fun dtStopMillis(): Long = dt!!.times(1000) + (MILLIS_3H)
    fun range3h(): LongRange = dtStartMillis()..(dtStopMillis())

    companion object {
        private val MILLIS_3H = TimeUnit.HOURS.toMillis(3)
    }
}

data class City(@Expose @SerializedName("id") var id: Long?,
                @Expose @SerializedName("name") var name: String?,
                @Expose @SerializedName("coord") var coord: Coord?,
                @Expose @SerializedName("country") var country: String?)

data class Coord(@Expose @SerializedName("lon") var longitude: Double?,
                 @Expose @SerializedName("lat") var latitude: Double?)


data class Wind(@Expose @SerializedName("speed") var speed: Double?,
                @Expose @SerializedName("deg") var deg: Double?)

data class Snow(@Expose @SerializedName("3h") var volume: Double?)

data class Rain(@Expose @SerializedName("3h") var precipitation: Double?)


data class Weather(@Expose @SerializedName("id") var id: Long?,
                   @Expose @SerializedName("main") var main: String?,
                   @Expose @SerializedName("description") var description: String?,
                   @Expose @SerializedName("icon") var icon: String?)

