package com.example.currencyconverter.ui.list_currency.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.data.room.CurrencyRoomDatabase
import com.example.currencyconverter.data.room.RepositoryInitialization
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private var realization: RepositoryRealization) : ViewModel() {
//    private val repository: RetrofitRepository = RetrofitRepository()
    private val repository: CurrencyRepository = DependencyInjection.repository

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

    fun getLocalCurrencyList(): MutableList<Currency>{
        return runBlocking {
            realization.getRoomCurrencyList()
        }
    }


    fun insertFavoriteCurrency(currency: Currency, onSuccess:() -> Unit){
       viewModelScope.launch(Dispatchers.IO){
           realization.insertFavoriteCurrency(currency){
                onSuccess()
            }
        }
    }

    fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListFavoriteCurrency(currency){
                onSuccess()
            }
        }
    }

    fun updateListCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListCurrency(currency){
                onSuccess()
            }
        }
    }
}


