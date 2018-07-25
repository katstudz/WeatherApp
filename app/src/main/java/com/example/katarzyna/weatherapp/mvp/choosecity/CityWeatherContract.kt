package com.example.katarzyna.weatherapp.mvp.choosecity

import android.location.Location
import com.example.katarzyna.weatherapp.datamodel.Ob
import com.example.katarzyna.weatherapp.mvp.BaseContract
import com.example.katarzyna.weatherapp.utils.EnumError
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum

class CityWeatherContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun getWeatherInfoForCity(cityName: String)
        fun getLastCityName():String
        fun checkCityNameCorrectSetAsFavourite(cityName: String)
        fun getWeatherForAcctualPosition(location : Location)
    }

    interface View: BaseContract.BaseView {
        fun setWeatherInfoForCity(observation: Ob)
        fun setWeatherIcon(weatherCondition: WeatherConditionEnum)
        fun showErrorAlert(error: EnumError)
        fun setCityAsFavourite(cityName: String)
        fun setAcctualLocationCityName(cityName: String)
    }
}