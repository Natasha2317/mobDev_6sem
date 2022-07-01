package com.example.currencyconverter.data.room

import android.content.Context
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.data.room.repository.RepositoryRealization

object RepositoryInitialization {
    private lateinit var repositoryRealization: RepositoryRealization
    private var currencyDao: CurrencyDao? = null

    fun getRepository(context: Context): RepositoryRealization {
        if (currencyDao == null) {
            currencyDao = CurrencyRoomDatabase.getInstance(context).getCurrencyDao()
            repositoryRealization = RepositoryRealization(currencyDao!!)
        }
        return repositoryRealization
    }
}