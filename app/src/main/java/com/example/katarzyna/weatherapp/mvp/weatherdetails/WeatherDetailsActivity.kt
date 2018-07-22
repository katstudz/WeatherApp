package com.example.katarzyna.weatherapp.mvp.weatherdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.utils.Common
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.weather_details_activity.*


class WeatherDetailsActivity: AppCompatActivity(), WeatherDetailsContract.View {

    lateinit var weatherDetailsPresenter: WeatherDetailsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details_activity)
        weatherDetailsPresenter = WeatherDetailsPresenter(Common.API_KEY)
        weatherDetailsPresenter.attachView(this)
        weatherDetailsPresenter.getForecast("Gdynia")

    }

    override fun drawChart(){
        println("OHOHOHOHO")
    }

    override fun drawChart(chartEntries: ArrayList<BarEntry>) {
        this.runOnUiThread( {
            val dataSet = BarDataSet(chartEntries, "")
            dataSet.color = resources.getColor(R.color.colorPrimary)
            dataSet.valueTextColor = resources.getColor(R.color.colorPrimary)

            val tempData = BarData(dataSet)
            chart.data = tempData
            chart.description.isEnabled = false
            chart.setDrawGridBackground(false)

            chart.axisLeft.setDrawGridLines(false)
            chart.xAxis.setDrawGridLines(false)

            chart.axisLeft.mAxisMinimum = 265F
            chart.axisLeft.mAxisMinimum = 320F
            chart.axisRight.setDrawLabels(false)
            chart.xAxis.setDrawLabels(false)

            chart.legend.isEnabled = false
            chart.notifyDataSetChanged()
            chart.invalidate()

        })
    }
}