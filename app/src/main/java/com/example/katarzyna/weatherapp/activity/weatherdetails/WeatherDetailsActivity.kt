package com.example.katarzyna.weatherapp.activity.weatherdetails

import android.graphics.Color
import android.os.Bundle
import android.view.View.GONE
import android.widget.ImageView
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.activity.BasicActivity
import com.example.katarzyna.weatherapp.utils.Common
import com.example.katarzyna.weatherapp.utils.WeatherDescriptior
import com.github.mikephil.charting.data.*
import kotlinx.android.synthetic.main.weather_details_activity.*


class WeatherDetailsActivity: BasicActivity(), WeatherDetailsContract.View {
    override fun setDescription(weatherDescriptior: WeatherDescriptior) {
        val descriptionText = weatherDescriptior.getDescription(this)
        weather_forecast_description.setText(descriptionText)
    }

    lateinit var weatherDetailsPresenter: WeatherDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details_activity)
        chart.setNoDataText("")

        weatherDetailsPresenter = WeatherDetailsPresenter(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret))
        weatherDetailsPresenter.attachView(this)

        val cityName = intent.getStringExtra(Common.CITY_NAME)
        weatherDetailsPresenter.initData(cityName)
        city_name.text = cityName
        setButtons()
    }

    private fun setButtons() {
        next.setColorFilter(Color.GRAY)
        past.setOnClickListener {
            weatherDetailsPresenter.drawPastData()
            changeArrowState(past, next)
        }

        next.setOnClickListener {
            weatherDetailsPresenter.drawForecastData()
            changeArrowState(next, past)
        }
    }

    override fun hideProgressBar() {
        progress_bar.visibility = GONE
    }

    private fun changeArrowState(inactive: ImageView, active: ImageView){
        inactive.setColorFilter(Color.GRAY)
        active.setColorFilter(R.color.colorPrimaryDark)
    }

    override fun drawChart(chartEntries: ArrayList<BarEntry>) {

            val dataSet = BarDataSet(chartEntries, "")
            dataSet.setDrawValues(false)
            dataSet.color = resources.getColor(R.color.colorPrimary)
            dataSet.valueTextColor = resources.getColor(R.color.colorPrimary)

            val tempData = BarData(dataSet)
            chart.data = tempData
            chart.description.isEnabled = false
            chart.setDrawGridBackground(false)

            chart.axisLeft.setDrawGridLines(false)
            chart.xAxis.setDrawGridLines(false)

            chart.axisRight.setDrawLabels(false)
            chart.xAxis.setDrawLabels(false)

            chart.legend.isEnabled = false
            chart.notifyDataSetChanged()
            chart.invalidate()

    }

}