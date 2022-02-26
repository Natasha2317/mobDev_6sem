package com.example.currencyconverter.data.room

import android.content.Context
import androidx.room.*
import com.example.currencyconverter.data.ListConverter
import com.example.currencyconverter.data.MapConverter


@Database(entities = [Currency::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class, MapConverter::class)
abstract class CurrencyRoomDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        var database: CurrencyRoomDatabase? = null

        fun getInstance(context: Context): CurrencyRoomDatabase? {
            if (database == null) {
                synchronized(this) {
                        val db = Room.databaseBuilder(
                            context.applicationContext,
                            CurrencyRoomDatabase::class.java, "currency_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    database = db
                    return db
                }
            }
            return database
        }
    }
}
