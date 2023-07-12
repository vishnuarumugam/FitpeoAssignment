package com.example.fitpeoassignment.common

import android.util.Log
import com.example.fitpeoassignment.common.Api
import com.example.fitpeoassignment.common.base.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class HttpClient {


    private fun getHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->

                val request = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            }.addInterceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                Log.e("addInterceptorLogs", ":" + response.code)
                if (response.code == 401) {

                }
                response
            }.build()
    }

    fun getHttpApi(): Api? {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Api::class.java)
    }
}