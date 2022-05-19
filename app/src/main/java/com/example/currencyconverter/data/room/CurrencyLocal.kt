package com.example.currencyconverter.data.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverter.models.Currency
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class CurrencyLocal(
    @SerializedName("name")
    @PrimaryKey @NonNull val name: String,
    @SerializedName("value") val value: Double,
    var isFavorite: Boolean,
//    @SerializedName("success") val success: Boolean,
//    @SerializedName("timestamp") val timestamp: Long,
//    @SerializedName("base") val base: String,
//    @SerializedName("date") val date: String,
//    @SerializedName("rates") val rates: Map<String, Double>
) {
//    fun toCurrency(): Currency = Currency(
//        name = name,
//        value = value,
//        isFavorite = false
//    )

}