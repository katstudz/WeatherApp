package com.example.katarzyna.weatherapp

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.katarzyna.weatherapp.datamodel.WeatherData
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {


    var CITY_WEATHER_FRAGMENT= "CITY_WEATHER_FRAGMENT"
    var DETAILS_WEATEHR_FRAGMENT = "DETAILS_WEATEHR_FRAGMENT"

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.basic_weather_info)

        val openWeatherMap = ApiClient.create()
        openWeatherMap.getWeather("e7fdc6f6a7f81c97bdd2ccb1c8ecd0e4","Kraków")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: WeatherData ->
                    println(response.cityName)
                    println(response.main)
                }, { error ->
                    println(error.message)
                })

    }
}
