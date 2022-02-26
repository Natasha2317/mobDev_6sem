package com.example.currencyconverter.data

import com.example.currencyconverter.data.api.CurrencyApi
import com.example.currencyconverter.data.api.RetrofitInstance
import com.example.currencyconverter.data.room.Currency
import com.example.currencyconverter.data.room.CurrencyDao
import com.example.currencyconverter.models.CurrencyResponse
import retrofit2.Response


class CurrencyRepository() {
    private val remoteDataSource: RemoteDataSource = RemoteDataSource()

    suspend fun getRetrofitCurrencyList(): Response<CurrencyResponse> {
        return remoteDataSource.getRetrofitCurrencyList()
    }
//    suspend fun getRoomCurrencyList(): Currency = localDataSource.getRoomCurrencyList()


}