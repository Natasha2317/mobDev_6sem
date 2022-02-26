package com.example.currencyconverter.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.CurrencyRepository
import com.example.currencyconverter.data.api.RetrofitInstance
import com.example.currencyconverter.models.CurrencyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var currencyRepository: CurrencyRepository = CurrencyRepository()
    val data: MutableLiveData<CurrencyResponse> by lazy {
        MutableLiveData()
    }
    fun init(){
        viewModelScope.launch(Dispatchers.IO){
//            val currencies = RetrofitInstance.service.getCurrencyList()
            val currencies = currencyRepository.getRetrofitCurrencyList()
            val currencyResponse = currencies.body()
            data.postValue(currencyResponse)
        }
    }

}