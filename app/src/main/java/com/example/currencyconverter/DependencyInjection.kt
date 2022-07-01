package com.example.currencyconverter

import com.example.currencyconverter.repository.CurrencyRepository
import com.example.currencyconverter.data.api.repository.RetrofitRepository

object DependencyInjection {

    private val retrofitRepository = RetrofitRepository()

    val repository = CurrencyRepository(
        retrofitRepository
    )
}