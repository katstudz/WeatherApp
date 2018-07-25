package com.example.katarzyna.weatherapp.mvp.choosecity

import android.location.Location
import com.example.katarzyna.weatherapp.datamodel.*
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.example.katarzyna.weatherapp.utils.CloudType
import com.example.katarzyna.weatherapp.utils.EnumError
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityWeatherPresenter(private val clientId: String, private val clientSecret: String) : CityWeatherContract.Presenter {

    private val openWeatherMap = ApiClient.create()
    private lateinit var view: CityWeatherContract.View
    private lateinit var lastPlace: Place

    override fun attachView(view: CityWeatherContract.View) {
        this.view = view
    }

    override fun getWeatherInfoForCity(cityName: String) { //TODO function to lambda
       println("XXXX")
        openWeatherMap.getAcctualObservations("Gdynia", clientId , clientSecret)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe({ response: AerisObservation ->
                   if (response.success)
                       setWeather(response.response)
                   else
                       view.showErrorAlert(EnumError.LOCATION_NOT_FOUND)
               }, { error ->
                   view.showErrorAlert(EnumError.INTERNET_CONNECTION)
               })
    }

    private fun setWeather(response: Response){
        view.setWeatherInfoForCity(response.ob)
        val weatherCondition = getWeatherCondition(response.ob)
        view.setWeatherIcon(weatherCondition)
        lastPlace = response.place
    }

    private fun getWeatherCondition(ob: Ob): WeatherConditionEnum
    {
        if (ob.tempC < 5)
            return WeatherConditionEnum.COLD
        else if (ob.humidity > 60)
            return WeatherConditionEnum.WET
        else if (CloudType.getCovarageInProcent(ob.cloudsCoded) > 0.7)
            return WeatherConditionEnum.CLOUDLY
        else
            return WeatherConditionEnum.SUNNY
    }

    override fun checkCityNameCorrectSetAsFavourite(cityName: String) {
        openWeatherMap.getCityWeather(clientSecret, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: WeatherResponse -> //TODO
                    view.setCityAsFavourite(cityName)
                }, { error ->
                    view.showErrorAlert(EnumError.INTERNET_CONNECTION) //TODO two other error message?
                })
    }

    override fun getLastCityName(): String {
        return lastPlace.name + ","+lastPlace.country
    }

    override fun getWeatherForAcctualPosition(location : Location) {
        openWeatherMap.getLocationWeather(clientSecret,location.latitude, location.longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: WeatherResponse ->
//                    setWeather(response)
//                    view.setAcctualLocationCityName(response.cityName!!)
                    println(response.main)
                }, { error ->
                    view.showErrorAlert(EnumError.INTERNET_CONNECTION)
                })
    }


}