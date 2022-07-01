package com.example.currencyconverter.ui.list_currency.viewmodel

import androidx.lifecycle.*
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.LongCurrency
import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.repository.LongCurrencyDtoMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(private var realization: RepositoryRealization) : ViewModel() {
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


    private fun insertCurrencyList(currency: Currency, onSuccess:() -> Unit){
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

    private suspend fun getLongCurrency(): LongCurrency {
        return realization.getLongCurrency()
    }

    fun longClickExchange(currency: Currency){

        viewModelScope.launch(Dispatchers.IO){
           realization.updateLongCurrency(LongCurrencyDtoMapper.mapCurrencyToLongCurrency(currency))
        }
    }
}


