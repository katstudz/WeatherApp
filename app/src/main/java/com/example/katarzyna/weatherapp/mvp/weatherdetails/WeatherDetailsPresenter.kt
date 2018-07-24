package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.datamodel.Forecast
import com.example.katarzyna.weatherapp.datamodel.ForecastResponse
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class WeatherDetailsPresenter(private val apiKey: String) :WeatherDetailsContract.Presenter{

    private lateinit var view: WeatherDetailsContract.View
    private var openWeatherMap = ApiClient.create()
    private lateinit var todayForecast:ArrayList<BarEntry>
    private lateinit var tomorrowForecast: ArrayList<BarEntry>
    private val TWO_DAYS = 4L
    private val TODAY= 8
    private val TOMORROW = 16

    override fun getForecast(cityName: String) {
        openWeatherMap.getForecast(apiKey, cityName, TWO_DAYS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: ForecastResponse ->
                    setData(response)
                }, { error ->
                    println(error.message)
                })
    }

    private fun setData(response: ForecastResponse){
        println("XOXOXODODODODO")
        val chartEntries = forecastDataToChartEntries(response)
        view.drawChart()
        view.drawChart(chartEntries)
        println("XOXOX")
    }

    private fun forecastDataToChartEntries(forecastResponse: ForecastResponse): ArrayList<BarEntry>{
        val chartEntries = ArrayList<BarEntry>()
        for(forecastHour: Forecast in forecastResponse.forecastList){

            val hour = getHourFromStringDate(forecastHour.dateString!!)
            chartEntries.add(BarEntry(hour.toFloat(), forecastHour.main!!.temp.toFloat()))
        }
//        todayForecast = chartEntries.subList(0, TODAY) as ArrayList<BarEntry>
//        tomorrowForecast = chartEntries.subList(TODAY, TOMORROW) as ArrayList<BarEntry>

        return chartEntries
    }

    private fun getHourFromStringDate(date: String): Int {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH)
        cal.time = sdf.parse(date)
        return cal.time.hours
    }

    override fun attachView(view: WeatherDetailsContract.View) {
        this.view = view
    }
}