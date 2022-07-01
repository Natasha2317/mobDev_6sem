package com.example.currencyconverter.data.room.repository

import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.models.LongCurrency

interface LocalCurrencyRepository {

    // Currencies List
    suspend fun getRoomCurrencyList(): MutableList<Currency>

    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    suspend fun insertCurrencyList(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess:() -> Unit)

    // Exchange
    suspend fun getRubInfo(): Currency

    suspend fun getUsdInfo(): Currency

    suspend fun insertLongCurrency(currency: LongCurrency)

    suspend fun updateLongCurrency(currency: LongCurrency)

    suspend fun getLongCurrency(): LongCurrency

    // Exchange history
    suspend fun addExchangeHistory(exchangeHistory: ExchangeHistory, onSuccess:() -> Unit)

    suspend fun getExchangeHistory(): MutableList<ExchangeHistory>
}