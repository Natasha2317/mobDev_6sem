package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.models.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getRoomFavoriteCurrencyList(): LiveData<List<Currency>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteCurrency(currency: Currency)

    @Delete
    suspend fun deleteFavoriteCurrency(currency: Currency)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateListCurrency(currency: Currency)
}