package com.example.data.repository

import com.example.data.model.CurrencyDao
import com.example.data.model.CurrencyInfo
import org.koin.core.component.KoinComponent

class CurrencyRepository(private val currencyDao: CurrencyDao): KoinComponent {

    suspend fun setLocalCurrency(currencyInfo: CurrencyInfo) {
        currencyDao.setCurrencies(currencyInfo)
    }

    fun getAllCurrencies() = currencyDao.getAllCurrencies()

    fun getCrypto() = currencyDao.getCrypto()

    fun getFiat() = currencyDao.getFiat()

    suspend fun deleteAllCurrency() = currencyDao.deleteAllCurrencies()

}