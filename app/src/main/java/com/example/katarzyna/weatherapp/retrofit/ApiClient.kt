package com.example.katarzyna.weatherapp.retrofit

import com.aerisweather.aeris.model.AerisResponse
import com.example.katarzyna.weatherapp.datamodel.AerisObservation
import com.example.katarzyna.weatherapp.datamodel.ForecastResponse
import com.example.katarzyna.weatherapp.datamodel.WeatherResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//TODO change server
// Access ID
//ePuXiCXumO1LDJMVBPraZ
//
//Secret Key
//rEvvdmJjWXCJwcWdrm9WV2KDR5vNtXsQhSov1NOe


interface ApiClient {

    @GET("observations/{city}")
    fun getAcctualObservations(@Path("city") city: String, @Query("client_id") clientId: String, @Query("client_secret") clientSecret: String): Observable<AerisObservation>

    @GET("observations/archive/{p}")
    fun getPastObservations(@Path("p") city: String, @Query("client_id") clientId: String, @Query("client_secret") clientSecret: String): Observable<AerisResponse>

    @GET("forecasts/{p}")
    fun getForecastObservations(@Path("p") city: String, @Query("from") from: String, @Query("client_id") clientId: String, @Query("client_secret") clientSecret: String): Observable<AerisResponse>



    @GET("weather")
    fun getCityWeather(@Query("apikey")  apiKey:String,
                       @Query("q") city: String): Observable<WeatherResponse>

    @GET("weather")
    fun getLocationWeather(@Query("apikey")  apiKey:String,
                           @Query("lat") latitude: Double,
                           @Query("lon") longitude: Double): Observable<WeatherResponse>


    @GET("forecast")
    fun getForecast(@Query("apikey")  apiKey:String,
                    @Query("q") city: String,
                    @Query("cnt") count: Long = 0): Observable<ForecastResponse>


    companion object Factory {

        var API_BASE_URL = "http://api.aerisapi.com/"

        private fun createClient(): OkHttpClient {
//            setLogLevel(RestAdapter.LogLevel.FULL)

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            val httpClient = OkHttpClient.Builder()
                httpClient.addInterceptor(logging)
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


