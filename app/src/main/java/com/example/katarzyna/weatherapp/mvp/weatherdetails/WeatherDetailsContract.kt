package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.mvp.BaseContract

class WeatherDetailsContract {
    interface Presenter: BaseContract.BasePresenter<View>{
        fun getWeatherHistoryForCity(cityName: String)
    }

    interface View: BaseContract.BaseView {
    }
}