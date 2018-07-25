package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.mvp.BaseContract
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import java.util.ArrayList

class WeatherDetailsContract {
    interface Presenter: BaseContract.BasePresenter<View>{
        fun downloadData(cityName: String)
        fun setForecastData()
        fun setPastData()
    }

    interface View: BaseContract.BaseView {
        fun drawChart(chartEntries: ArrayList<BarEntry>)
    }
}