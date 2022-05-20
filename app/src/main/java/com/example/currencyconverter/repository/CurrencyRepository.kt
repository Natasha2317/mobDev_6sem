package com.example.currencyconverter.repository

import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.LocalDataSource
import com.example.currencyconverter.data.RemoteDataSource
import com.example.currencyconverter.data.api.CurrencyApi
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.models.Currencies


open class CurrencyRepository(
//    private val localDataSource: LocalDataSource,
//    private val remoteDataSource: RemoteDataSource,
    private val retrofitRepository: RetrofitRepository

    ) {
//    private val remoteDataSource: RemoteDataSource = TODO()

    suspend fun getRetrofitCurrencyList(): Currencies? {
        return retrofitRepository.getRetrofitCurrencyList()
    }

//    suspend fun getRoomCurrencyList(): LiveData<List<Currency>> {
//        return localDataSource.getRoomCurrencyList()
//    }
}