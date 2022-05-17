package com.example.currencyconverter.ui.exchange.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository


class ExchangeViewModelFactory(private val currencyRepository: RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            ExchangeViewModel(this.currencyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}