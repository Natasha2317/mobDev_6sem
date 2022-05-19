package com.example.currencyconverter.data.room.repository

import androidx.lifecycle.LiveData
import com.example.currencyconverter.data.room.CurrencyLocal
import com.example.currencyconverter.data.room.dao.CurrencyDao
import com.example.currencyconverter.models.Currency
import com.example.currencyconverter.repository.CurrencyDtoMapper

class RepositoryInitializer(private val currencyDao: CurrencyDao): LocalCurrencyRepository {

    //    private var currencyDao: CurrencyDao? = null
//    private lateinit var currencyRepository: CurrencyRepository
//
//    fun getRepository(context: Context): CurrencyRepository {
//        if (currencyDao == null) {
//            currencyDao = CurrencyRoomDatabase.getInstance(context)?.currencyDao()
////            currencyRepository = DependencyInjection.repository
//        }
//        return currencyRepository
//    }
    override val getRoomFavoriteCurrencyList: LiveData<List<Currency>>
        get() = currencyDao.getRoomFavoriteCurrencyList()

    override suspend fun insertFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.insertFavoriteCurrency(currency)
        onSuccess()
    }

    override suspend fun deleteFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.deleteFavoriteCurrency(currency)
        onSuccess()
    }

    override suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.updateListCurrency(currency)
        onSuccess()
    }

}