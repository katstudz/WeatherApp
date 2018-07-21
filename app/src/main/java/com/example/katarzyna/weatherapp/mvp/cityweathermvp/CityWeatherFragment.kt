package com.example.katarzyna.weatherapp.mvp.cityweathermvp

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katarzyna.weatherapp.R

class CityWeatherFragment:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.basic_weather_fragment,
                container, false)
    }
}