package com.example.currencyconverter.data.room.repository

import android.content.Context
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.data.room.CurrencyRoomDatabase
import com.example.currencyconverter.repository.CurrencyRepository

object RepositoryInitializer{

    private var currencyDao: CurrencyDao? = null
    private lateinit var currencyRepository: CurrencyRepository

    fun getRepository(context: Context): CurrencyRepository {
        if (currencyDao == null) {
            currencyDao = CurrencyRoomDatabase.getInstance(context)?.currencyDao()
//            currencyRepository = DependencyInjection.repository
        }
        return currencyRepository
    }
}