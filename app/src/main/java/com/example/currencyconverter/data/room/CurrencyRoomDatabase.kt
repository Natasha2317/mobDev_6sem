package com.example.currencyconverter.data.room

import android.content.Context
import androidx.room.*
import com.example.currencyconverter.data.room.converters.ListConverter
import com.example.currencyconverter.data.room.converters.MapConverter
import androidx.room.Database
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.models.Currency


@Database(entities = [Currency::class], version = 2, exportSchema = false)
@TypeConverters(ListConverter::class, MapConverter::class)
abstract class CurrencyRoomDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        var database: CurrencyRoomDatabase? = null

        fun getInstance(context: Context): CurrencyRoomDatabase {
            return if (database === null) {
                database = Room.databaseBuilder(
                        context, CurrencyRoomDatabase::class.java, "currency_database"
                    ).fallbackToDestructiveMigration()
                    .build()
                    database as CurrencyRoomDatabase
                }else{
                database as CurrencyRoomDatabase
                }
        }

    }
}


