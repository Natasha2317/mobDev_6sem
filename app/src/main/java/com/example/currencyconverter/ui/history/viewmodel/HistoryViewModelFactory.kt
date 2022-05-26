package com.example.currencyconverter.ui.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.repository.RepositoryRealization

class HistoryViewModelFactory(private val repositoryRealization: RepositoryRealization): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(this.repositoryRealization) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}