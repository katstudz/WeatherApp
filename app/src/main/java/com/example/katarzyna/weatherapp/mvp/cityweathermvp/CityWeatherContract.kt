package com.example.katarzyna.weatherapp.mvp.cityweathermvp

import com.example.katarzyna.weatherapp.datamodel.WeatherData
import com.example.katarzyna.weatherapp.mvp.BaseContract

class CityWeatherContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun getWeatherInfoForCity(cityName: String)
    }

    interface View: BaseContract.BaseView {
        fun updateWeatherInfoForCity( cityWeatherInfo: WeatherData)
    }
}