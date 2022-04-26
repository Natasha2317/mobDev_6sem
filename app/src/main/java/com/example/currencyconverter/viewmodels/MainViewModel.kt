package com.example.currencyconverter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.DependencyInjection.repository
import com.example.currencyconverter.data.room.Currency
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {
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


