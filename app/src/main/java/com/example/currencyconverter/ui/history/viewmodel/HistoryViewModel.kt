package com.example.currencyconverter.ui.history.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.room.repository.RepositoryRealization
import com.example.currencyconverter.models.ExchangeHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HistoryViewModel(private var realization: RepositoryRealization) : ViewModel() {

    val data: MutableLiveData<MutableList<ExchangeHistory>> by lazy {
        MutableLiveData()
    }
    private val listMonthHistory: MutableList<ExchangeHistory> = mutableListOf()

    init {
        getExchangeHistory()
    }

    private fun getExchangeHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(realization.getExchangeHistory())
        }
    }

    fun getMonthHistory(){
        viewModelScope.launch(Dispatchers.IO) {
            realization.getExchangeHistory().let { it ->
                it.map { item ->
                    dateConverter(item)
                }
            }
            data.postValue(listMonthHistory)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun dateConverter(item: ExchangeHistory) {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val date = LocalDate.parse(item.exchange_date, formatter)
        if (date.month == LocalDate.now().month){
            listMonthHistory.add(item)
        }
    }
}


