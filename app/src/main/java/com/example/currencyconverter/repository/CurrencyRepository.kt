package com.example.currencyconverter.repository

import com.example.currencyconverter.data.api.repository.RetrofitRepository
import com.example.currencyconverter.models.Currencies


open class CurrencyRepository(
    private val retrofitRepository: RetrofitRepository

    ) {

    suspend fun getRetrofitCurrencyList(): Currencies? {
        return retrofitRepository.getRetrofitCurrencyList()
    }

}