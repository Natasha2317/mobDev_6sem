package com.example.currencyconverter.data.room

import android.content.Context
import androidx.room.*
import androidx.room.Database
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.models.ExchangeHistory
import com.example.currencyconverter.models.LongCurrency


@Database(entities = [Currency::class, ExchangeHistory::class, LongCurrency::class], version = 6, exportSchema = false)
abstract class CurrencyRoomDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        var database: CurrencyRoomDatabase? = null

        fun getInstance(context: Context): CurrencyRoomDatabase {
            return if (database === null) {
                database = Room.databaseBuilder(
                        context, CurrencyRoomDatabase::class.java, "currency_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    database as CurrencyRoomDatabase
                }else{
                database as CurrencyRoomDatabase
                }
        }

    }
}


