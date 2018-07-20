package com.example.katarzyna.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.katarzyna.weatherapp.retrofit.ApiClient
import com.example.katarzyna.weatherapp.retrofit.OwmInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.R.attr.apiKey
import com.example.katarzyna.weatherapp.datamodel.WeatherResponse
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

//        val client: ApiClient = ApiClient.Factory.create()


//        val interceptor = OwmInterceptor("e7fdc6f6a7f81c97bdd2ccb1c8ecd0e4")
        val client = OkHttpClient()
//        client.interceptors().add(interceptor)
        client.networkInterceptors() //.add(CacheControlInterceptor(10 * 60L, 10 * 60L))
        val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .build()
        val openWeatherMap = retrofit.create(ApiClient::class.java)


        val resp = openWeatherMap.getWeather("e7fdc6f6a7f81c97bdd2ccb1c8ecd0e4","Kraków")

        resp.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                println("ERROR onFailitrure")
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                println("ON RESPONSE")

            }
        })

    }

}
