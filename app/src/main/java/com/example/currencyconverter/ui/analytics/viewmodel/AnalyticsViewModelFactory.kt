package com.example.currencyconverter.ui.analytics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository

class AnalyticsViewModelFactory(private val currencyRepository: RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AnalyticsViewModel::class.java)) {
            AnalyticsViewModel(this.currencyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}