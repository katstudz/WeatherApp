package com.example.katarzyna.weatherapp.mvp.weatherdetails

import com.example.katarzyna.weatherapp.datamodel.*
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.github.mikephil.charting.data.BarEntry
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*



class WeatherDetailsPresenter(private val clientId: String, private val clientSecret: String)  :WeatherDetailsContract.Presenter{
    private lateinit var view: WeatherDetailsContract.View
    private var openWeatherMap = ApiClient.create()
    private lateinit var forecastBarEntry:ArrayList<BarEntry>
    private lateinit var yesterdayBarEntry: ArrayList<BarEntry>

    override fun setForecastData() {
        view.drawChart(forecastBarEntry)
    }

    override fun setPastData() {
        view.drawChart(yesterdayBarEntry)
    }

    override fun downloadData(cityName: String){
        getForecast(cityName)
        getPastObservations(cityName)
    }

    private fun getForecast(cityName: String) {
        openWeatherMap.getForecastObservations(cityName,"1hr","now",24 ,clientId , clientSecret)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ forecastResponse: ForecastResponse ->
                    setForecastData(forecastResponse.response.first().periods)

                },
                        { error -> //todo handle more error
                })
    }

    private fun getPastObservations(cityName: String){
        openWeatherMap.getPastObservations(cityName,"-24hours", 48 ,clientId , clientSecret)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({pastObservation: PastObservation? ->
                    println(pastObservation!!.success)
                    prepareYesterdayData(pastObservation.response.periods)
                },
                        {
                            error -> //todo handle more error
                            println("OOOOOOOOOOOOOO")
                        })
    }


    private fun prepareYesterdayData(periods: List<PastObservationPeriods>) {
        val chartEntries = ArrayList<BarEntry>()
        for (period in periods){
            val calendar = getHour(period.ob.dateTimeISO)
            if(isFullHour(calendar!!))
                chartEntries.add(BarEntry(calendar!!.time.hours.toFloat(), period.ob.tempC.toFloat()))
        }
        yesterdayBarEntry = chartEntries.subList(0, 24).toList() as ArrayList<BarEntry>
    }

    private fun isFullHour(calendar: Calendar):Boolean{
        return calendar!!.time.minutes == 0
    }

    private fun setForecastData(forecastPeriods: List<Period>) {
        forecastBarEntry = forecastDataToChartEntries(forecastPeriods)
        setForecastData()
    }

    private fun forecastDataToChartEntries(forecastPeriods: List<Period>): ArrayList<BarEntry>{
        val chartEntries = ArrayList<BarEntry>()
        for(forecastForNextHour: Period in forecastPeriods){
            val calendar = getHour(forecastForNextHour.validTime)
            chartEntries.add(BarEntry(calendar!!.time.hours.toFloat(), forecastForNextHour.avgTempC.toFloat()))
        }
        return chartEntries
    }

    private fun getHour(time:String): Calendar? {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        calendar.time = sdf.parse(time)
        return calendar
    }



    override fun attachView(view: WeatherDetailsContract.View) {
        this.view = view
    }
}