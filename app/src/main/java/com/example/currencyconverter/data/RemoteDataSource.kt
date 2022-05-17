package com.example.currencyconverter.data

import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.data.api.CurrencyApi
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.Response

class RemoteDataSource(private val currencyApi: CurrencyApi) {

    suspend fun getRetrofitCurrencyList(): CurrencyResponse {
        return currencyApi.getRetrofitCurrencyList()
    }


}
