package com.example.currencyconverter.data.room.repository

import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.models.LongCurrency

class RepositoryRealization(private var currencyDao: CurrencyDao): LocalCurrencyRepository {


    override suspend fun getRoomCurrencyList(): MutableList<Currency>{
        return currencyDao.getRoomCurrencyList()
    }

    override suspend fun getFavoriteCurrencyList(): MutableList<Currency>? {
        return currencyDao.getFavoriteCurrencyList()
    }

    override suspend fun insertCurrencyList(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.insertCurrencyList(currency)
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

    override suspend fun addExchangeHistory(exchangeHistory: ExchangeHistory, onSuccess: () -> Unit) {
        currencyDao.addExchangeHistory(exchangeHistory)
        onSuccess()
    }

    override suspend fun getExchangeHistory(): MutableList<ExchangeHistory> {
        return currencyDao.getExchangeHistory()
    }

    override suspend fun getRubInfo(): Currency {
        return currencyDao.getRubInfo()
    }

    override suspend fun getUsdInfo(): Currency {
        return currencyDao.getUsdInfo()
    }

    override suspend fun insertLongCurrency(currency: LongCurrency) {
        return currencyDao.insertLongCurrency(currency)
    }

    override suspend fun updateLongCurrency(currency: LongCurrency) {
        currencyDao.updateLongCurrency(currency.name,  currency.value, currency.isFavorite)
    }

    override suspend fun getLongCurrency(): LongCurrency {
        return currencyDao.getLongCurrency()
    }

}