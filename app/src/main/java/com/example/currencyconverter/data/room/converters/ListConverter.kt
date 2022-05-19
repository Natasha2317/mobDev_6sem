package com.example.currencyconverter.data.room.converters

import androidx.room.TypeConverter
import com.example.currencyconverter.data.room.CurrencyLocal
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken


class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<CurrencyLocal> {
        val listType = object : TypeToken<List<CurrencyLocal>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<CurrencyLocal>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

