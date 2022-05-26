package com.example.currencyconverter.ui.history.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HistoryViewModel(private var realization: RepositoryRealization) : ViewModel() {
    val data: MutableLiveData<Currencies> by lazy {
        MutableLiveData()
    }
    fun getExchangeHistory(): MutableList<ExchangeHistory> {
        return runBlocking {
            realization.getExchangeHistory()
        }
    }


}


