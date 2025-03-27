package com.example.data.repository

import com.example.data.model.CurrencyDao
import com.example.data.model.CurrencyInfo
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent

@Single
class CurrencyRepository(private val currencyDao: CurrencyDao): KoinComponent {

    suspend fun setLocalCurrency(currencyInfo: CurrencyInfo) {
        currencyDao.setCurrencies(currencyInfo)
    }

    fun getAllCurrencies() = currencyDao.getAllCurrencies()

    fun getCrypto() = currencyDao.getCrypto()

    fun getFiat() = currencyDao.getFiat()

    fun getFilteredCurrencies(keyword: String) = currencyDao.getFilteredCurrencies(keyword)

    suspend fun deleteAllCurrency() = currencyDao.deleteAllCurrencies()

}