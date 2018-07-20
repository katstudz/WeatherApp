package com.example.katarzyna.weatherapp.retrofit

import com.example.katarzyna.weatherapp.datamodel.WeatherResponse
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable
import retrofit2.Call


interface ApiClient {

    @GET("weather")
    fun getWeather(@Query("apikey")  apiKey:String, @Query("q") city: String): Call<WeatherResponse>

    //Call<ForecastWeatherInfo>

    companion object Factory {

        var API_BASE_URL = "http://api.openweathermap.org/data/2.5/"

        private fun createClient(): OkHttpClient {
                val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor {
                    chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                return httpClient.build()
        }

        fun create():ApiClient{
            val retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createClient())
                    .build()
//                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
//                    .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())

//                    .baseUrl(URL)
//                    .build()

            return retrofit.create(ApiClient::class.java)
        }
    }
}


