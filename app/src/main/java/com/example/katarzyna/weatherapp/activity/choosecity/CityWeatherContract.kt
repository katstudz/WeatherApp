package com.example.katarzyna.weatherapp.activity.choosecity

import android.location.Location
import com.example.katarzyna.weatherapp.datamodel.Ob
import com.example.katarzyna.weatherapp.activity.BaseContract
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum

class CityWeatherContract {

    interface Presenter: BaseContract.BasePresenter<View>{
        fun getAcctualObservation(cityName: String)
        fun getLastCityName():String
        fun getWeatherForAcctualPosition(location : Location)
    }

    interface View: BaseContract.BaseView {
        fun setWeatherInfoForCity(observation: Ob)
        fun setWeatherIcon(weatherCondition: WeatherConditionEnum)
        fun setAcctualLocationCityName(cityName: String)
    }
}