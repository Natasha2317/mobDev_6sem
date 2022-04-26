package com.example.currencyconverter.data.api

import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET


interface CurrencyApi {


    @GET("/api/latest?access_key=c74e48f783b2008346b29126369eea4b&format=1")
    suspend fun getCurrencyList(): CurrencyResponse

}