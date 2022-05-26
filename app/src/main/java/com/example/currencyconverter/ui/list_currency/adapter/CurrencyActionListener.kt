package com.example.currencyconverter.ui.list_currency.adapter

import com.example.currencyconverter.models.Currency


interface CurrencyActionListener {

    fun onCurrencyFavorite(currency: Currency)

    fun currencyExchange(currency: Currency)

    fun currencyUp(currencyUp: Currency)

}