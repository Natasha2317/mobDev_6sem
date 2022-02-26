package com.example.currencyconverter.viewmodels

import android.content.Context
import com.example.currencyconverter.data.CurrencyRepository
import com.example.currencyconverter.data.LocalDataSource
import com.example.currencyconverter.data.RemoteDataSource
import com.example.currencyconverter.data.room.CurrencyDao
import com.example.currencyconverter.data.room.CurrencyRoomDatabase

object Repositories {
//    private lateinit var applicationContext: Context
//    private val database: CurrencyRoomDatabase by lazy<CurrencyRoomDatabase> {
//        Room.databaseBuilder(applicationContext, CurrencyRoomDatabase::class.java, "database.db")
//            .createFromAsset("initial_database.db")
//            .build()
//    }
//    private var currencyDao: CurrencyDao? = null
//    private lateinit var currencyRepository: CurrencyRepository
//    private lateinit var remoteDataSource: RemoteDataSource
//    private lateinit var localDataSource: LocalDataSource
//
//    fun getRepository(context: Context): CurrencyRepository {
//        if (currencyDao == null) {
//            currencyDao = CurrencyRoomDatabase.getInstance(context)?.currencyDao()
//            currencyRepository = CurrencyRepository(localDataSource, remoteDataSource, currencyDao!!)
//        }
//        return currencyRepository
//    }
}