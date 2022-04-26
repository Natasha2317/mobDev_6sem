package com.example.currencyconverter

import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.data.LocalDataSource
import com.example.currencyconverter.data.RemoteDataSource
import com.example.currencyconverter.data.api.CurrencyApi
import com.example.currencyconverter.data.room.CurrencyDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit: Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://data.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val service: CurrencyApi = retrofit.create(CurrencyApi::class.java)

        private val localDataSource = LocalDataSource()
        private val remoteDataSource = RemoteDataSource(service)

        val repository = CurrencyRepository(
            localDataSource,
            remoteDataSource,
        )
}