package com.rizieq.berita.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {




    companion object {

        private const val BASE_URL = "http://newsapi.org/v2/"

        fun create(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }



        private fun provideLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        }

        private fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(provideLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build()

        }


    }



}