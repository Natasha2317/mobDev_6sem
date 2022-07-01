package com.example.currencyconverter.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


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
