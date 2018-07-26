package com.example.katarzyna.weatherapp.activity.choosecity

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

    override fun getAcctualObservation(place: String) {
        var reformedPlaceName= place.replace(" ", "+")

        if(checkCityNameCorrect(reformedPlaceName)){
            openWeatherMap.getAcctualObservations(reformedPlaceName, clientId , clientSecret)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response: AerisObservation ->
                        setWeather(response.response)
                    }, { error ->
                        view.showErrorAlert(EnumError.OTHER)
                    })
        }

        else view.showErrorAlert(EnumError.NO_CITYNAME_FORMAT)
    }

    private fun checkCityNameCorrect(cityName: String):Boolean{
        var splitedCityName = cityName.split(',')
        if (splitedCityName.size != 2)
            return false
        return ((splitedCityName[0].isNotEmpty())and (splitedCityName[1].isNotEmpty()))
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


    override fun getLastCityName(): String {
        return lastPlace.getString()
    }

    override fun getWeatherForAcctualPosition(location : Location) {

        val locationString = "${location.latitude.toString().format(2)}," +
                location.longitude.toString().format(2)
        openWeatherMap.getAcctualObservations(locationString, clientId , clientSecret)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ aerisobservation: AerisObservation ->
                    setWeather(aerisobservation.response)
                    view.setAcctualLocationCityName(aerisobservation.response.place.getString())
                }, { error ->
                    view.showErrorAlert(EnumError.OTHER)
                })
    }



}