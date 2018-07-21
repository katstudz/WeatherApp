package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.datamodel.ForecastResponse
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class WeatherDetailsPresenter(private val apiKey: String) :WeatherDetailsContract.Presenter{

    private lateinit var view: WeatherDetailsContract.View
    private var openWeatherMap = ApiClient.create()

    override fun getWeatherHistoryForCity(cityName: String) {
        openWeatherMap.getForecast(apiKey, cityName, 8L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: ForecastResponse ->
                    println(response.forecastList)
                }, { error ->
                    println(error.message)
                })
    }

    override fun attachView(view: WeatherDetailsContract.View) {
        this.view = view
    }
}