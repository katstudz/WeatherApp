package com.example.katarzyna.weatherapp.mvp.choosecity

import android.location.Location
import com.example.katarzyna.weatherapp.datamodel.WeatherResponse
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityWeatherPresenter(private val apiKey: String) : CityWeatherContract.Presenter {

    private val openWeatherMap = ApiClient.create()
    private lateinit var view: CityWeatherContract.View
    private lateinit var lastCityName: String

    override fun attachView(view: CityWeatherContract.View) {
        this.view = view
    }

    override fun getWeatherInfoForCity(cityName: String) { //TODO function to lambda
       openWeatherMap.getCityWeather(apiKey,cityName)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({ response: WeatherResponse ->
                   setWeather(response)
               }, { error ->
                   view.showErrorAlert()
               })
    }

    private fun setWeather(weatherResponse: WeatherResponse){
        view.setWeatherInfoForCity(weatherResponse)
        val weatherCondition = getWeatherCondition(weatherResponse)
        view.setWeatherIcon(weatherCondition)
        lastCityName = weatherResponse.cityName!!
    }

    private fun getWeatherCondition(weatherResponse: WeatherResponse): WeatherConditionEnum
    {
        if (weatherResponse.main!!.temp < 0)
            return WeatherConditionEnum.COLD
        else if (weatherResponse.main!!.humidity > 60)
            return WeatherConditionEnum.WET
        else if (weatherResponse.clouds!!.all > 70)
            return WeatherConditionEnum.CLOUDLY
        else
            return WeatherConditionEnum.SUNNY
    }

    override fun checkCityNameCorrectSetAsFavourite(cityName: String) {
        openWeatherMap.getCityWeather(apiKey, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: WeatherResponse ->
                    view.setCityAsFavourite(cityName)
                }, { error ->
                    view.showErrorAlert() //TODO two other error message?
                })
    }

    override fun getLastCityName(): String {
        return lastCityName
    }

    override fun getWeatherForAcctualPosition(location : Location) {
        openWeatherMap.getLocationWeather(apiKey,location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: WeatherResponse ->
                    setWeather(response)
                    view.setAcctualLocationCityName(response.cityName!!)
                    println(response.main)
                }, { error ->
                    view.showErrorAlert()
                })
    }


}