package com.example.currencyconverter.ui.list_currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository


//class MainViewModelFactory(private val retrofitRepository: RetrofitRepository): ViewModelProvider.Factory {
//
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            MainViewModel(this.retrofitRepository) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
//}