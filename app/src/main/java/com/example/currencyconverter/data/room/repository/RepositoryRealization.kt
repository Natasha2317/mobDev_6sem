package com.example.currencyconverter.data.room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.data.room.CurrencyRoomDatabase
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.repository.CurrencyDtoMapper

class RepositoryRealization(private var currencyDao: CurrencyDao): LocalCurrencyRepository {


    override suspend fun getRoomCurrencyList(): MutableList<Currency>{
        return currencyDao.getRoomCurrencyList()
    }

    override suspend fun insertFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.insertFavoriteCurrency(currency)
        onSuccess()
    }

    override suspend fun deleteFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.deleteFavoriteCurrency(currency)
        onSuccess()
    }

    override suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.updateListFavoriteCurrency(currency.name,  currency.isFavorite)
        onSuccess()
    }

    override suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.updateListCurrency(currency.name,  currency.value)
        onSuccess()
    }

}