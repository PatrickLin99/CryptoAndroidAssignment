package com.example.search

import com.example.data.model.CurrencyDao
import com.example.data.model.CurrencyInfo
import com.example.data.repository.CurrencyRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrencyRepositoryTest {

    private lateinit var repository: CurrencyRepository
    private val currencyDao = mockk<CurrencyDao>(relaxed = true) // Mock UserDao

    @Before
    fun setup() {
        repository = CurrencyRepository(currencyDao)
    }

    @Test
    fun addRepository() = runTest {
        val currency = CurrencyInfo(
            id = "BTC",
            _name = "Bitcoin",
            _symbol = "BTC",
            code = null
        )
        repository.setLocalCurrency(currency)

        coVerify { currencyDao.setCurrencies(currency) }
    }

    @Test
    fun getCurrencyReturnsFlow() = runTest {
        val currencies = listOf(CurrencyInfo(
            id = "BTC",
            _name = "Bitcoin",
            _symbol = "BTC",
            code = null
        ))
        every { currencyDao.getAllCurrencies() } returns flowOf(currencies)

        val result = repository.getAllCurrencies().first()
        assertEquals(currencies, result)
    }
}