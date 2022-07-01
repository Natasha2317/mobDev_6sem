package com.example.currencyconverter.repository

import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.LongCurrency

object LongCurrencyDtoMapper {

    fun mapCurrencyToLongCurrency(currency: Currency): LongCurrency {
        return LongCurrency(
            id = 1,
            name = currency.name,
            value = currency.value,
            isFavorite = currency.isFavorite
        )
    }
    fun mapLongCurrencyToCurrency(currency: LongCurrency): Currency {
        return Currency(
            name = currency.name,
            value = currency.value,
            isFavorite = currency.isFavorite
        )
    }
}