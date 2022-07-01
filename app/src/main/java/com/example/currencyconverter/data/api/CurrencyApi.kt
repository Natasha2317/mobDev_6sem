package com.example.currencyconverter.data.api

import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.http.GET


interface CurrencyApi {

    @GET("/api/latest?access_key=fd6536675a46dddfe79915b2b92a3ece&format=1")
    suspend fun getRetrofitCurrencyList(): CurrencyResponse

}