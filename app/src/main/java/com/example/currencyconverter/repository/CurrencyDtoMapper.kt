package com.example.currencyconverter.repository

import com.example.currencyconverter.models.Currencies
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.CurrencyResponse
import java.time.Instant


object CurrencyDtoMapper {

    fun mapResponseToDomainModel(response: CurrencyResponse): Currencies {
        return Currencies(
            date = Instant.ofEpochSecond(response.timestamp).toString(),
            base = response.base,
            rates = response.rates.toList().map {
                Currency(it.first, it.second, false)
            }
        )
    }
}