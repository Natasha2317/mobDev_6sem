package com.example.currencyconverter.data.room

import androidx.room.*

@Dao

interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    suspend fun getRoomCurrencyList(): Currency
}