package com.example.currencyconverter.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Currencies(
    var base: String,
    var date: String,
    var rates: List<Currency>
)
@Entity(tableName = "currencies")
data class Currency(
    @SerializedName("name")
    @PrimaryKey @NonNull val name: String,
    @SerializedName("value") val value: Double,
    var isFavorite: Boolean,
): Serializable
