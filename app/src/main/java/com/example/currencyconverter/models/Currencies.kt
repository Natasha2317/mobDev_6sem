package com.example.currencyconverter.models

import java.io.Serializable
import java.util.*

data class Currencies(
    var base: String,
    var date: String,
    var rates: List<Currency>
)

data class Currency(
    var name: String,
    var value: Double,
    var favorite: Boolean
)
