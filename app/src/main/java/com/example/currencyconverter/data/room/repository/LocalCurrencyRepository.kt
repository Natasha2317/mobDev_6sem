package com.example.currencyconverter.data.room.repository

import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.models.Currency

interface LocalCurrencyRepository {

    val getRoomFavoriteCurrencyList: LiveData<List<Currency>>

    suspend fun insertFavoriteCurrency(currency: Currency, onSuccess:() -> Unit)

    suspend fun deleteFavoriteCurrency(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess:() -> Unit)
}