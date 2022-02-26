package com.example.currencyconverter.data

import com.example.currencyconverter.data.room.Currency
import com.example.currencyconverter.data.room.CurrencyDao

interface LocalDataSource {

    val currencyDao: CurrencyDao

    suspend fun getRoomCurrencyList(): Currency {
        return currencyDao.getRoomCurrencyList()
    }
}