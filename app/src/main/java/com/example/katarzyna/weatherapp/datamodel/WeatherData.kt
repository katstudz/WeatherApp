package com.example.katarzyna.weatherapp.datamodel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WeatherData(
                           @Expose @SerializedName("main") var main: Main?,
                           @Expose @SerializedName("clouds") var clouds: Clouds?,

                           @Expose @SerializedName("name") var cityName: String?)
