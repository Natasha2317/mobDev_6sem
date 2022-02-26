package com.example.currencyconverter.data.room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverter.models.CurrencyResponse
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currencies")
data class Currency(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Long,
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double>
) {
    fun toCurrency(): CurrencyResponse = CurrencyResponse(
        success = success,
        timestamp = timestamp,
        base = base,
        date = date,
        rates = rates
    )

}