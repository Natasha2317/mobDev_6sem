package com.example.currencyconverter.data.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class Currency(
    @SerializedName("name")
    @PrimaryKey @NonNull val name: String,
    @SerializedName("value") val value: Double,
    val b: Boolean,
//    @SerializedName("success") val success: Boolean,
//    @SerializedName("timestamp") val timestamp: Long,
//    @SerializedName("base") val base: String,
//    @SerializedName("date") val date: String,
//    @SerializedName("rates") val rates: Map<String, Double>
) {
//    fun toCurrency(): CurrencyResponse = CurrencyResponse(
//        name = name,
//        value = value,
//        base = base,
//        date = date,
//        rates = rates
//    )

}