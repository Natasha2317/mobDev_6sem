package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.models.Currency

@Dao
interface CurrencyDao {
//    @Query("SELECT * FROM currencies")
//    fun getRoomCurrencyList(): LiveData<List<Currency>>

    @Query("SELECT * FROM currencies")
    suspend fun getRoomCurrencyList(): MutableList<Currency>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteCurrency(currency: Currency)

    @Delete
    suspend fun deleteFavoriteCurrency(currency: Currency)

    @Query("UPDATE currencies SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, isFavorite: Boolean)

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)
}