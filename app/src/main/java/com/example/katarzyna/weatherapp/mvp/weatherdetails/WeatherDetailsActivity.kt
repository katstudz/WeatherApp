package com.example.katarzyna.weatherapp.mvp.weatherdetails

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.katarzyna.weatherapp.R
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.weather_details_activity.*


class WeatherDetailsActivity: AppCompatActivity(), WeatherDetailsContract.View {

    lateinit var weatherDetailsPresenter: WeatherDetailsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details_activity)
        weatherDetailsPresenter = WeatherDetailsPresenter(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret))
        weatherDetailsPresenter.attachView(this)
        weatherDetailsPresenter.downloadData("Gdynia,pl")

        setButtons()
    }

    private fun setButtons() {
        next.setColorFilter(Color.GRAY)
        past.setOnClickListener {
            weatherDetailsPresenter.setPastData()
            changeArrowState(past, next)
        }

        next.setOnClickListener {
            weatherDetailsPresenter.setForecastData()
            changeArrowState(next, past)
        }
    }

    private fun changeArrowState(inactive: ImageView, active: ImageView){
        inactive.setColorFilter(Color.GRAY)
        active.setColorFilter(R.color.colorPrimaryDark)
    }

    override fun drawChart(chartEntries: ArrayList<BarEntry>) {
        this.runOnUiThread( {
            val dataSet = BarDataSet(chartEntries, "")
            dataSet.setDrawValues(false)
            dataSet.color = resources.getColor(R.color.colorPrimary)
            dataSet.valueTextColor = resources.getColor(R.color.colorPrimary)

            val tempData = BarData(dataSet)
            chart.data = tempData
            chart.description.isEnabled = false
            chart.setDrawGridBackground(false)
            chart.setDrawValueAboveBar(false)

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