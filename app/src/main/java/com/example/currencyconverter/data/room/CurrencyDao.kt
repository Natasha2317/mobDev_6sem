package com.example.currencyconverter.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getRoomCurrencyList(): LiveData<List<Currency>>
}