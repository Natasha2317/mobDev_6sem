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

    val data: MutableLiveData<List<Currency>> by lazy {
        MutableLiveData()
    }

    init {
        getCurrencyList()
    }



    private fun getRetrofitCurrencyList(){
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(getLocalCurrencyList())
            repository.getRetrofitCurrencyList().let { remoteCurrencies ->
                remoteCurrencies?.rates?.map { remoteCurrency ->
                    updateListCurrency(remoteCurrency){}
                }
                data.postValue(getLocalCurrencyList())
            }
        }
    }

    private fun getLocalCurrencyList(): MutableList<Currency>{
        return runBlocking {
            realization.getRoomCurrencyList()
        }
    }


    fun insertCurrencyList(currency: Currency, onSuccess:() -> Unit){
       viewModelScope.launch(Dispatchers.IO){
           realization.insertCurrencyList(currency){
                onSuccess()
            }
        }
    }

    fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListFavoriteCurrency(currency){
                val updated = getLocalCurrencyList()
                data.postValue(updated)
                onSuccess()
            }
        }
    }

    private fun updateListCurrency(currency: Currency, onSuccess:() -> Unit){
        viewModelScope.launch(Dispatchers.IO){
            realization.updateListCurrency(currency){
                onSuccess()
            }
        }
    }

    fun update(){
        getRetrofitCurrencyList()
    }

    private fun getCurrencyList(){
        return data.postValue(getLocalCurrencyList())
    }

    fun longClickExchange(currency: Currency){
        viewModelScope.launch(Dispatchers.IO){
            val index = getLocalCurrencyList().indexOf(currency)
            getLocalCurrencyList().removeAt(index)
            getLocalCurrencyList().add(0, currency)
            val updated = getLocalCurrencyList()
            data.postValue(updated)
        }
    }
}


