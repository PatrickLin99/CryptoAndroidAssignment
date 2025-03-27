package com.example.search

import com.example.data.model.CurrencyInfo
import com.example.data.repository.CurrencyRepository
import com.example.search.presentation.model.CurrencyFilterType
import com.example.search.presentation.viewModel.DemoViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DemoViewModelTest {

    private lateinit var vm: DemoViewModel
    private val repository = mockk<CurrencyRepository>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        every { repository.getAllCurrencies() } returns flowOf(listOf(CurrencyInfo.defaultInstance))
        vm = DemoViewModel(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testViewInfoSuccess() = runTest {
        val mockItems = listOf(
            CurrencyInfo(
                id = "BTC",
                _name = "Bitcoin",
                _symbol = "BTC",
                code = null
            ),
            CurrencyInfo(
                id = "ETH",
                _name = "Ethereum",
                _symbol = "ETH",
                code = null
            ),
            CurrencyInfo(
                id = "JPY",
                _name = "Japanese Yen",
                _symbol = "¥",
                code = "JPY"
            ),
            CurrencyInfo(
                id = "USD",
                _name = "United States Dollar",
                _symbol = "$",
                code = "USD"
            ),
        )
        every { repository.getAllCurrencies() } returns flowOf(mockItems)

        vm.initCurrenciesObserver()
        vm.currencyType.emit(CurrencyFilterType.ALL)

        val job = launch {
            vm.viewInfo.collect {
                if (it.currencies.isNotEmpty()) {
                    assertEquals(mockItems, it.currencies)
                }
            }
        }
        advanceUntilIdle()
        job.cancel()
    }
}