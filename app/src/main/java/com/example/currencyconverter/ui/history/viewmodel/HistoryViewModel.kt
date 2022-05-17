package com.example.currencyconverter.ui.history.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(private val currencyRepository: RetrofitRepository) : ViewModel() {
    val data: MutableLiveData<Currencies> by lazy {
        MutableLiveData()
    }


    fun init(){
        viewModelScope.launch(Dispatchers.IO){
            currencyRepository.getRetrofitCurrencyList().let {
                data.postValue(it)
            }
        }

    }


}


