package com.example.currencyconverter.ui.list_currency.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RetrofitRepository) : ViewModel() {
    val data: MutableLiveData<Currencies> by lazy {
        MutableLiveData()
    }

    fun getRetrofitCurrencyList(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getRetrofitCurrencyList().let {
                data.postValue(repository.getRetrofitCurrencyList())
            }
        }

    }


}


