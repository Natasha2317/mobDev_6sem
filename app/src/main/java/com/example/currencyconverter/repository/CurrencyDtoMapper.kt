package com.example.currencyconverter.repository

import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.CurrencyResponse
import com.example.currencyconverter.models.Currency

object CurrencyDtoMapper {

    fun mapResponseToDomainModel(response: CurrencyResponse): Currencies {
        return Currencies(
            date = response.date,
            base = response.base,
            rates = response.rates.toList().map {
                Currency(it.first, it.second, false)
            }
        )
    }
}