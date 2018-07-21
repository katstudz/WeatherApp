package com.example.katarzyna.weatherapp.mvp.choosecity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.datamodel.WeatherData
import com.example.katarzyna.weatherapp.mvp.weatherdetails.WeatherDetailsActivity
import com.example.katarzyna.weatherapp.utils.Common
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.basic_weather_info.*


class ChooseCityActivity : AppCompatActivity(), CityWeatherContract.View {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var cityWeatherPresenter: CityWeatherPresenter
    private val FAVOURITE_CITY_KEY = "FAVOURITE_CITY_KEY"
    private val NONE = "NONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic_weather_info)
        cityWeatherPresenter = CityWeatherPresenter(Common.API_KEY)
        cityWeatherPresenter.attachView(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        fusedLocationClient.lastLocation
//                .addOnSuccessListener { location : Location? ->
//                    // Got last known location. In some rare situations this can be null.
//                }

        setFavouritePlaceWeather()
        setButtons()
    }

    private fun setFavouritePlaceWeather() {
        val myPrefs = this.getSharedPreferences(Common.FILE, Context.MODE_PRIVATE)
        val cityName= myPrefs.getString(FAVOURITE_CITY_KEY, NONE)

        if(cityName!= NONE){
            cityWeatherPresenter.getWeatherInfoForCity(cityName)
            cityNameEditText.setText(cityName, TextView.BufferType.EDITABLE);
        }

    }

    private fun setButtons(){
        findCityWeather.setOnClickListener {
            hideKeyboard(this)
            cityWeatherPresenter.getWeatherInfoForCity(cityNameEditText.text.toString())
        }


        setCityAsFavourite.setOnClickListener {
            cityWeatherPresenter.checkCityNameCorrectSetAsFavourite(cityNameEditText.text.toString())
        }


        moreDetails.setOnClickListener {
            val weatherDetailsIntent = Intent(this, WeatherDetailsActivity::class.java)
            weatherDetailsIntent.putExtra(Common.CITY_NAME, cityWeatherPresenter.getLastCityName())
            startActivity(weatherDetailsIntent)
        }
    }

    override fun setCityAsFavourite(cityName: String) {
        val myPrefs = this.getSharedPreferences(Common.FILE, Context.MODE_PRIVATE)
        val editor = myPrefs.edit()
        editor.putString(FAVOURITE_CITY_KEY, cityName)
        editor.apply()
    }


    override fun setWeatherInfoForCity(cityWeatherInfo: WeatherData) {
        temperature.text = "${cityWeatherInfo.main!!.temp.toString()} K"
        humidity.text = "${getString(R.string.humidity)} ${cityWeatherInfo.main!!.humidity}%"
        cloudy.text = "${getString(R.string.clouds)} ${cityWeatherInfo.clouds!!.all}%"
        moreDetails.visibility = VISIBLE
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
