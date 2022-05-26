package com.example.currencyconverter.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.currencyconverter.data.room.converters.DateConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*

@Entity(tableName = "exchange_history")
data class ExchangeHistory(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") @NonNull val id: Int,
    @SerializedName("name_currency1") val name_currency1: String,
    @SerializedName("name_currency2") val name_currency2: String,
    @SerializedName("value_currency1") val value_currency1: Double,
    @SerializedName("value_currency2") val value_currency2: Double,
    @SerializedName("exchange_date") val exchange_date: String,
): Serializable
