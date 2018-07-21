package com.example.katarzyna.weatherapp.mvp.choosecity

import com.example.katarzyna.weatherapp.datamodel.WeatherData
import com.example.katarzyna.weatherapp.mvp.BaseContract
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum

class CityWeatherContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun getWeatherInfoForCity(cityName: String)
        fun getLastCityName():String
        fun checkCityNameCorrectSetAsFavourite(cityName: String)
    }

    interface View: BaseContract.BaseView {
        fun setWeatherInfoForCity( cityWeatherInfo: WeatherData)
        fun setWeatherIcon(weatherCondition: WeatherConditionEnum)
        fun showErrorAlert()
        fun setCityAsFavourite(cityName: String)
    }
}