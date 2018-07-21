package com.example.katarzyna.weatherapp.mvp.choosecity

import com.example.katarzyna.weatherapp.datamodel.WeatherData
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityWeatherPresenter(val apiKey: String) : CityWeatherContract.Presenter {
    private val openWeatherMap = ApiClient.create()
    private lateinit var view: CityWeatherContract.View
    private lateinit var lastCityName: String

    override fun attachView(view: CityWeatherContract.View) {
        this.view = view
    }

    override fun getWeatherInfoForCity(cityName: String) {
       openWeatherMap.getWeather(apiKey,cityName)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({ response: WeatherData ->
                   setWeather(response)
                   lastCityName = response.cityName!!
               }, { error ->
                   view.showErrorAlert()
               })
    }

    override fun getLastCityName(): String {
        return lastCityName
    }


    private fun setWeather(weatherData: WeatherData){
        view.setWeatherInfoForCity(weatherData)
        val weatherCondition = getWeatherCondition(weatherData)
        view.setWeatherIcon(weatherCondition)
    }

    private fun getWeatherCondition(weatherData: WeatherData): WeatherConditionEnum
    {
        if (weatherData.main!!.temp < 0)
            return WeatherConditionEnum.COLD
        else if (weatherData.main!!.humidity > 60)
            return WeatherConditionEnum.WET
        else if (weatherData.clouds!!.all > 70)
            return WeatherConditionEnum.CLOUDLY
        else
            return WeatherConditionEnum.SUNNY
    }





}