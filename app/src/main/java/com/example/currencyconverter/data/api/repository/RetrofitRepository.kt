package com.example.currencyconverter.data.api.repository

import com.example.currencyconverter.data.api.RetrofitInstance
import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.CurrencyResponse
import com.example.currencyconverter.repository.CurrencyDtoMapper

class RetrofitRepository {

    suspend fun getRetrofitCurrencyList(): Currencies? {
        return try {
            val response = RetrofitInstance.service.getRetrofitCurrencyList()
            CurrencyDtoMapper.mapResponseToDomainModel(response)
        } catch(e: Exception){
            null
        }
    }
}