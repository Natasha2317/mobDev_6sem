package com.example.currencyconverter.data.api

import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET


interface CurrencyApi {

    @GET("/api/latest?access_key=50eff39862f6aab9060144e1829e559d&format=1")
    suspend fun getRetrofitCurrencyList(): CurrencyResponse

}