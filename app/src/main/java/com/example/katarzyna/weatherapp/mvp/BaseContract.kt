package com.example.katarzyna.weatherapp.mvp

interface BaseContract {
    interface BasePresenter<BaseView>{
        fun attachView(view : BaseView)
    }

    interface BaseView

}