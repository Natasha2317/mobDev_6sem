package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.data.room.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getRoomCurrencyList(): LiveData<List<Currency>>
}