package com.example.currencyconverter.ui.filters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository

class FiltersViewModelFactory(private val currencyRepository: RetrofitRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FiltersViewModel::class.java)) {
            FiltersViewModel(this.currencyRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}