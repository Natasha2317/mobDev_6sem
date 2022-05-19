package com.example.currencyconverter.ui.list_currency.adapter

import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.models.Currency


interface CurrencyActionListener {

    fun onCurrencyFavorite(currency: Currency)

    fun currencyExchange(currency: Currency)

}