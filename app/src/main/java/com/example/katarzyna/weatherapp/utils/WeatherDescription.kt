package com.example.katarzyna.weatherapp.utils

import android.content.Context
import com.example.katarzyna.weatherapp.R

class WeatherDescription {
    var temp:TempEnum = TempEnum.NO_CHANGE
    var moreRainy = false

    private var SUBIECTIVE_EXTREMA_CHANGE_TEMP_LIMIT  = 10
    private var SUBIECTIVE_CHANGE_TEMP_LIMIT  = 5
    private var SUBIECTIVE_HUMIDITY_LIMIT  = 0.2

    fun setDescriptionParam(yesterday:DayAvr, future:DayAvr){

        if (yesterday.avrHumidity + SUBIECTIVE_HUMIDITY_LIMIT < future.avrHumidity)
            moreRainy = true

        if(yesterday.avrTemp + SUBIECTIVE_EXTREMA_CHANGE_TEMP_LIMIT < future.avrTemp )
            temp = TempEnum.EXTREMAL_WARMER
        else if(yesterday.avrTemp + SUBIECTIVE_CHANGE_TEMP_LIMIT < future.avrTemp )
            temp = TempEnum.WARMER
        else if(yesterday.avrTemp - SUBIECTIVE_CHANGE_TEMP_LIMIT > future.avrTemp )
            temp = TempEnum.COLDER
        else if(yesterday.avrTemp - SUBIECTIVE_EXTREMA_CHANGE_TEMP_LIMIT > future.avrTemp )
            temp = TempEnum.EXTREMAL_COLDER
    }

    fun getDescription(context: Context):String{
        var tempDescription:String
        when(temp){
            TempEnum.EXTREMAL_COLDER-> tempDescription = context.getString(R.string.EXTREMAL_COLDER)
            TempEnum.COLDER-> tempDescription = context.getString(R.string.COLDER)
            TempEnum.NO_CHANGE-> tempDescription = context.getString(R.string.NO_CHANGE)
            TempEnum.WARMER-> tempDescription = context.getString(R.string.WARMER)
            TempEnum.EXTREMAL_WARMER-> tempDescription = context.getString(R.string.EXTREMAL_WARMER)
        }

        return tempDescription
    }

}

class DayAvr(val avrHumidity:Double, val avrTemp:Double)


enum class TempEnum{
    EXTREMAL_COLDER, COLDER, NO_CHANGE,  WARMER, EXTREMAL_WARMER
}