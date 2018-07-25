package com.example.katarzyna.weatherapp.mvp

import com.example.katarzyna.weatherapp.utils.EnumError

interface BaseContract {
    interface BasePresenter<BaseView>{
        fun attachView(view : BaseView)
    }

    interface BaseView{
         fun showErrorAlert(enumError: EnumError)
         fun getErrorTextFromEnumError(enumError: EnumError): Int
    }

}