package com.example.currencyconverter.ui.exchange.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.models.LongCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ExchangeViewModel(private var realization: RepositoryRealization) : ViewModel() {


    fun getFavoriteCurrencyList(): MutableList<Currency>? {
        return runBlocking {
            realization.getFavoriteCurrencyList()
        }
    }

    fun addExchangeHistory(exchangeHistory: ExchangeHistory, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.addExchangeHistory(exchangeHistory){
                onSuccess()
            }
        }
    }

    fun getExchangeHistory(): MutableList<ExchangeHistory> {
        return runBlocking {
            realization.getExchangeHistory()
        }
    }

    fun getRubInfo(): Currency {
        return runBlocking {
            realization.getRubInfo()
        }
    }

    fun getUsdInfo(): Currency {
        return runBlocking {
            realization.getUsdInfo()
        }
    }

    fun getLongCurrency(): LongCurrency{
        return runBlocking {
            realization.getLongCurrency()
        }
    }
}


