package com.example.katarzyna.weatherapp.mvp.choosecity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.datamodel.WeatherData
import kotlinx.android.synthetic.main.basic_weather_info.*
import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


class ChooseCityActivity : AppCompatActivity(), CityWeatherContract.View {
    lateinit var cityWeatherPresenter:CityWeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_weather_info)
        cityWeatherPresenter = CityWeatherPresenter(resources.getString(R.string.api_weather_key))
        cityWeatherPresenter.attachView(this)
        setButtons()
    }

    fun setButtons(){
        findCityWeather.setOnClickListener {
            hideKeyboard(this)
            cityWeatherPresenter.getWeatherInfoForCity(cityNameEditText.text.toString())
        }

        moreDetails.setOnClickListener {
//            val mapIntent = Intent(this, MapsActivity::class.java)
//            mapIntent.putExtra(Common.DRONE_MESSAGE_KEY, drone_message)
//            startActivity(mapIntent)
        }
    }


    override fun setWeatherInfoForCity(cityWeatherInfo: WeatherData) {
        temperature.text = "${cityWeatherInfo.main!!.temp.toString()} K"
        humidity.text = "${getString(R.string.humidity)} ${cityWeatherInfo.main!!.humidity}%"
        cloudy.text = "${getString(R.string.clouds)} ${cityWeatherInfo.clouds!!.all}%"
    }

    override fun setWeatherIcon(weatherCondition: WeatherConditionEnum) { //TODO find more icon
        var icon:Drawable
        when (weatherCondition){
            WeatherConditionEnum.SUNNY-> icon = ContextCompat.getDrawable(this, R.drawable.ic_wb_sunny_black)!!
            WeatherConditionEnum.CLOUDLY-> icon = ContextCompat.getDrawable(this, R.drawable.ic_cloud_24dp)!!
            else -> {
                icon = ContextCompat.getDrawable(this, R.drawable.ic_search_24dp)!!
            }
        }

        weatherIcon.setImageDrawable(icon)
    }


    override fun showErrorAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.title_city_weather_error_message)
        builder.setMessage(R.string.text_city_weather_error_message)
        val dialog = builder.create()
        dialog.show()
        }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}
