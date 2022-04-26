package com.example.currencyconverter.adapter

import com.example.currencyconverter.models.Currency

interface CurrencyActionListener {

    fun onCurrencyFavorite(currency: Currency)

    fun currencyExchange(currency: Currency)
}