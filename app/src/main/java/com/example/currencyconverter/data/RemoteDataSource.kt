package com.example.currencyconverter.data

import com.example.currencyconverter.models.CurrencyResponse

interface RemoteDataSource {

    suspend fun getRetrofitCurrencyList(): CurrencyResponse

}
