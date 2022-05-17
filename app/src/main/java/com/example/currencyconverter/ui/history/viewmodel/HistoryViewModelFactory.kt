package com.example.currencyconverter.ui.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository

class HistoryViewModelFactory(private val currencyRepository: RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(this.currencyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}