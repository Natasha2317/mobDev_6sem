package com.example.currencyconverter.data.api

import com.example.currencyconverter.DependencyInjection
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.data.LocalDataSource
import com.example.currencyconverter.data.RemoteDataSource
import com.example.currencyconverter.data.api.CurrencyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitInstance {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://data.fixer.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}