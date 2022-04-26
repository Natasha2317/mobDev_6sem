package com.example.currencyconverter.data

import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.room.Currency
import com.example.currencyconverter.data.room.CurrencyDao

class LocalDataSource {
    lateinit var currencyDao: CurrencyDao

    suspend fun getRoomCurrencyList(): LiveData<List<Currency>> {
        return currencyDao.getRoomCurrencyList()
    }
}