package com.example.currencyconverter.data.room.dao

import androidx.room.*
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.models.LongCurrency

@Dao
interface CurrencyDao {

    // Currencies List
    @Query("SELECT * FROM currencies ORDER BY isFavorite DESC")
    suspend fun getRoomCurrencyList(): MutableList<Currency>

    @Query("SELECT * FROM currencies WHERE isFavorite = 1")
    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyList(currency: Currency)

    @Query("UPDATE currencies SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, isFavorite: Boolean)

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)

    // Exchange history
    @Query("SELECT * FROM currencies WHERE name='RUB'")
    suspend fun getRubInfo(): Currency

    @Query("SELECT * FROM currencies WHERE name='USD'")
    suspend fun getUsdInfo(): Currency

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLongCurrency(currency: LongCurrency)

    @Query("UPDATE long_currency SET isFavorite = :isFavorite, name = :name, value = :value WHERE id = 1")
    suspend fun updateLongCurrency(name: String, value: Double, isFavorite: Boolean)

    @Query("SELECT * FROM long_currency")
    suspend fun getLongCurrency(): LongCurrency

    // Exchange history
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExchangeHistory(exchangeHistory: ExchangeHistory)

    @Query("SELECT * FROM exchange_history")
    suspend fun getExchangeHistory(): MutableList<ExchangeHistory>

}