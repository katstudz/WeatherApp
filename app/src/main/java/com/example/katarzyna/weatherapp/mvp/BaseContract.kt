package com.example.katarzyna.weatherapp.mvp

import com.example.katarzyna.weatherapp.datamodel.WeatherData

interface BaseContract {
    interface BasePresenter<BaseView>{
        fun attachView(view : BaseView)
    }

    interface BaseView {
    }

}