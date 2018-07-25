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
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.katarzyna.weatherapp.R
import com.example.katarzyna.weatherapp.datamodel.Ob
import com.example.katarzyna.weatherapp.mvp.weatherdetails.WeatherDetailsActivity
import com.example.katarzyna.weatherapp.utils.CloudType
import com.example.katarzyna.weatherapp.utils.Common
import com.example.katarzyna.weatherapp.utils.EnumError
import com.example.katarzyna.weatherapp.utils.WeatherConditionEnum
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.choose_city_activity.*


class ChooseCityActivity : AppCompatActivity(), CityWeatherContract.View {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var cityWeatherPresenter: CityWeatherPresenter
    private val FAVOURITE_CITY_KEY = "FAVOURITE_CITY_KEY"
    private val NONE = "NONE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_city_activity)
        cityWeatherPresenter = CityWeatherPresenter(this.getString(R.string.aeris_client_id), this.getString(R.string.aeris_client_secret))
        cityWeatherPresenter.attachView(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        cityWeatherPresenter.getAcctualObservation("Gdynia,pl")
//        setFavouritePlaceWeather()
//        setButtons()

    }

    private fun setFavouritePlaceWeather() {
        val myPrefs = this.getSharedPreferences(Common.FILE, Context.MODE_PRIVATE)
        val cityName= myPrefs.getString(FAVOURITE_CITY_KEY, NONE)

        if(cityName!= NONE){
            cityWeatherPresenter.getAcctualObservation(cityName)
            city_name_edit_text.setText(cityName, TextView.BufferType.EDITABLE)
        }

    }

    private fun setButtons(){
        find_city_weather.setOnClickListener {
            hideKeyboard(this)
            cityWeatherPresenter.getAcctualObservation(city_name_edit_text.text.toString())
        }


        set_city_as_favourite.setOnClickListener {
            cityWeatherPresenter.checkCityNameCorrectSetAsFavourite(city_name_edit_text.text.toString())
        }

        acctual_position.setOnClickListener {
            setAcctualPosition()
        }

        more_details.setOnClickListener {
            val weatherDetailsIntent = Intent(this, WeatherDetailsActivity::class.java)
            weatherDetailsIntent.putExtra(Common.CITY_NAME, cityWeatherPresenter.getLastCityName())
            startActivity(weatherDetailsIntent)
        }
    }
    fun setAcctualPosition(){
        try {
            fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location? ->
                        println(location!!.latitude)
                        cityWeatherPresenter.getWeatherForAcctualPosition(location!!)
                    }
        }
        catch (e:SecurityException){
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.no_know_location_permission)
            val dialog = builder.create()
            dialog.show()
        }
    }

    override fun setAcctualLocationCityName(cityName: String) {
        city_name_edit_text.setText(cityName, TextView.BufferType.EDITABLE)
    }

    override fun setCityAsFavourite(cityName: String) {
        val myPrefs = this.getSharedPreferences(Common.FILE, Context.MODE_PRIVATE)
        val editor = myPrefs.edit()
        editor.putString(FAVOURITE_CITY_KEY, cityName)
        editor.apply()
    }

    override fun setWeatherInfoForCity(observation: Ob) {
        temperature.text = "${observation.tempC} C"
        humidity.text = "${getString(R.string.humidity)} ${observation.humidity}%"
        cloudly.text = "${getString(R.string.clouds)} ${CloudType.getCovarageInProcent(observation.cloudsCoded)}%"
        more_details.visibility = VISIBLE
        weather_icon.visibility = VISIBLE
        before_image.visibility = INVISIBLE

    }

    override fun setWeatherIcon(weatherCondition: WeatherConditionEnum) { //TODO find more icon
        var icon:Drawable = ContextCompat.getDrawable(this, R.drawable.ic_search_24dp)!!
        when (weatherCondition){
            WeatherConditionEnum.SUNNY-> icon = ContextCompat.getDrawable(this, R.drawable.ic_wb_sunny_black)!!
            WeatherConditionEnum.CLOUDLY-> icon = ContextCompat.getDrawable(this, R.drawable.ic_cloud_100dp)!!

        }
        weather_icon.setImageDrawable(icon)
    }


    override fun showErrorAlert(enumError: EnumError) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.title_city_weather_error_message)
        builder.setMessage(getErrorTextFromEnumError(enumError))
        val dialog = builder.create()
        dialog.show()
        }

    fun getErrorTextFromEnumError(enumError: EnumError): Int{
        when(enumError){
            EnumError.INTERNET_CONNECTION-> R.string.check_internet_connection
            EnumError.LOCATION_NOT_FOUND-> R.string.no_location
        }
        return R.string.text_city_weather_error_message
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
