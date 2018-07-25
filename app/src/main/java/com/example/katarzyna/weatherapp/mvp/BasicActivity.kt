package com.example.katarzyna.weatherapp.mvp

import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.utils.EnumError

abstract class BasicActivity: AppCompatActivity(), BaseContract.BaseView {
    override fun showErrorAlert(enumError: EnumError) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.title_city_weather_error_message)
        builder.setMessage(getErrorTextFromEnumError(enumError))
        val dialog = builder.create()
        dialog.show()
    }

    override fun getErrorTextFromEnumError(enumError: EnumError): Int{
        when(enumError){
            EnumError.INTERNET_CONNECTION-> return R.string.check_internet_connection
            EnumError.LOCATION_NOT_FOUND-> return R.string.no_location
            EnumError.OTHER-> return R.string.error
            EnumError.NO_LOCATION_PERMISSION-> R.string.no_know_location_permission
        }
        return R.string.text_city_weather_error_message
    }

}