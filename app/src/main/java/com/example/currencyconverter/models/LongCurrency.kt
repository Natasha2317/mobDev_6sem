package com.example.currencyconverter.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "long_currency")
data class LongCurrency(
    @PrimaryKey @NonNull
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("value") val value: Double,
    var isFavorite: Boolean,
)
