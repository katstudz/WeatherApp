package com.example.katarzyna.weatherapp.mvp.weatherdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.katarzyna.weatherapp.R
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.weather_details_activity.*


class WeatherDetailsActivity: AppCompatActivity() {
    lateinit var weatherDetailsPresenter: WeatherDetailsPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_details_activity)

        val entries = ArrayList<Entry>()

        entries.add(Entry(1.0F, 1.0F))
        entries.add(Entry(2.0F, 2.0F))
        entries.add(Entry(3.0F, 3.0F))
        entries.add(Entry(4.0F, 1.0F))

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = resources.getColor(R.color.colorPrimaryDark)
        dataSet.valueTextColor = resources.getColor(R.color.colorPrimary)
        val lineData = LineData(dataSet)

        chart.data = lineData
        chart.description.isEnabled = false
        chart.setDrawGridBackground(false)

        chart.axisLeft.mAxisMinimum = 265F
        chart.axisLeft.mAxisMinimum = 320F
        chart.axisRight.setDrawLabels(false)
        chart.xAxis.setDrawLabels(false)

        chart.legend.isEnabled = false
        chart.invalidate()

    }
}