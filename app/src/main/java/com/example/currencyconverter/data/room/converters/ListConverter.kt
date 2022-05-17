package com.example.currencyconverter.data.room.converters

import androidx.room.TypeConverter
import com.example.currencyconverter.data.room.Currency
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken


class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<Currency> {
        val listType = object : TypeToken<List<Currency>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Currency>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}

