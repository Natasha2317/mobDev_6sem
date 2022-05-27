package com.example.currencyconverter.data.room.repository

import androidx.lifecycle.LiveData
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory

interface LocalCurrencyRepository {

    // Currencies List
    suspend fun getRoomCurrencyList(): MutableList<Currency>

    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    suspend fun insertCurrencyList(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess:() -> Unit)

    // Exchange
    suspend fun addExchangeHistory(exchangeHistory: ExchangeHistory, onSuccess:() -> Unit)

    suspend fun getExchangeHistory(): MutableList<ExchangeHistory>

    suspend fun getRubInfo(): Currency

    suspend fun getUsdInfo(): Currency
}