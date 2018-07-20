package com.example.katarzyna.weatherapp.retrofit

import okhttp3.Interceptor
import retrofit2.Response

class OwmInterceptor(val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): okhttp3.Response? {
        val request = chain!!.request()

        val appidUrl = "${request.url()}&APPID=$apiKey"

        val apiKeyRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(appidUrl)
                .build()
        //todo: check if owm response is cached, if not try adding CacheControl to response header

        return chain.proceed(apiKeyRequest);
    }

}