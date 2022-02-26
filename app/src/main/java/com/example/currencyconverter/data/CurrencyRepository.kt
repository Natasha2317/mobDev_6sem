package com.example.currencyconverter.data


import com.example.currencyconverter.data.api.RetrofitInstance
import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.Response


class CurrencyRepository() {
    suspend fun getRetrofitCurrencyList(): Response<CurrencyResponse> {
        return RetrofitInstance.service.getCurrencyList()
    }




}