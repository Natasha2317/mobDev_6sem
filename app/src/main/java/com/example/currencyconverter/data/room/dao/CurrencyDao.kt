package com.example.currencyconverter.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory

@Dao
interface CurrencyDao {
//    @Query("SELECT * FROM currencies")
//    fun getRoomCurrencyList(): LiveData<List<Currency>>

    // Currencies List
    @Query("SELECT * FROM currencies")
    suspend fun getRoomCurrencyList(): MutableList<Currency>

    @Query("SELECT * FROM currencies WHERE isFavorite = 1")
    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyList(currency: Currency)

    @Query("UPDATE currencies SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, isFavorite: Boolean)

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)

    // Exchange
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExchangeHistory(exchangeHistory: ExchangeHistory)

    @Query("SELECT * FROM exchange_history")
    suspend fun getExchangeHistory(): MutableList<ExchangeHistory>

    @Query("SELECT * FROM currencies WHERE name='RUB'")
    suspend fun getRubInfo(): Currency

    @Query("SELECT * FROM currencies WHERE name='USD'")
    suspend fun getUsdInfo(): Currency
}