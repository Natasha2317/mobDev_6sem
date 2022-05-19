package com.example.currencyconverter.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.repository.CurrencyRepository

class CurrencyViewModel(application: Application): AndroidViewModel(application) {

    private lateinit var localData: LiveData<List<CurrencyLocal>>
    private lateinit var repository: CurrencyRepository
    val context = application

//    suspend fun init() {
//        val currencyDao = CurrencyRoomDatabase.getInstance(context).currencyDao()
//        repository = DependencyInjection.repository
//        localData = repository.getRoomCurrencyList(currencyDao)
//    }
//
//    suspend fun getRoomCurrencyList(): LiveData<List<Currency>>{
//        return localData
//    }
}