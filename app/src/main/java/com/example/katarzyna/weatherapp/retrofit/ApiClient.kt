package com.example.katarzyna.weatherapp.retrofit

import com.example.katarzyna.weatherapp.datamodel.ForecastResponse
import com.example.katarzyna.weatherapp.datamodel.WeatherData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiClient {

    @GET("weather")
    fun getAcctualWeatherForCity(@Query("apikey")  apiKey:String,
                                 @Query("q") city: String): Observable<WeatherData>

    @GET("forecast")
    fun getForecast(@Query("apikey")  apiKey:String,
                    @Query("q") city: String,
                    @Query("cnt") count: Long = 0): Observable<ForecastResponse>


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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(createClient())
                    .build()


            return retrofit.create(ApiClient::class.java)
        }
    }
}


