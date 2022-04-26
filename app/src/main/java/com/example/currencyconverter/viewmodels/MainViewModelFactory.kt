package com.example.currencyconverter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.repository.CurrencyRepository

class MainViewModelFactory (private val currencyRepository: CurrencyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.currencyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}