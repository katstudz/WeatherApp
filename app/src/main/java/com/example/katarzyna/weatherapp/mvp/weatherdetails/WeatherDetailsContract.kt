package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.mvp.BaseContract
import com.example.katarzyna.weatherapp.utils.WeatherDescriptior
import com.github.mikephil.charting.data.BarEntry
import java.util.ArrayList

class WeatherDetailsContract {
    interface Presenter: BaseContract.BasePresenter<View>{
        fun initData(cityName: String)
        fun drawForecastData()
        fun drawPastData()
    }

    interface View: BaseContract.BaseView {
        fun drawChart(chartEntries: ArrayList<BarEntry>)
        fun setDescription(descriptior: WeatherDescriptior)
        fun hideProgressBar()
    }
}