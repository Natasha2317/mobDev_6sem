package com.example.currencyconverter.repository

import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.LocalDataSource
import com.example.currencyconverter.data.RemoteDataSource
import com.example.currencyconverter.data.room.Currency
import com.example.currencyconverter.data.room.CurrencyDao
import com.example.currencyconverter.models.Currencies


class CurrencyRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,

    ) {

    suspend fun getRetrofitCurrencyList(): Currencies? {
        return try {
            val response = remoteDataSource.getRetrofitCurrencyList()
            CurrencyDtoMapper.mapResponseToDomainModel(response)
        } catch(e: Exception){
            null
        }
    }

    suspend fun getRoomCurrencyList(): LiveData<List<Currency>> {
        return localDataSource.getRoomCurrencyList()
    }
}