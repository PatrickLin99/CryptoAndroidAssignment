package com.example.search

import com.example.data.model.CurrencyInfo
import com.example.search.domanin.useCase.GetCurrencyMatchedUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
class UseCaseTest {

    private val firstExampleCurrency = CurrencyInfo(
        id = "id",
        _name = "Foobar",
        _symbol = "Symbol",
        code = null,
    )

    /**
     * Example 1 - 1
     * input: foo
     * condition: Foobar
     * isMatch: true
     */
    @Test
    fun isKeywordMatchExampleOne() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency, "foo"), true)
    }

    /**
     * Example 1 - 2
     * input: foo
     * condition: Barfoo
     * isMatch: false
     */
    @Test
    fun isKeywordNotMatchExampleOne() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_name = "Barfoo"), "foo"), false)
    }

    /**
     * Example 2 - 1
     * input: Classic
     * condition: Ethereum Classic
     * isMatch: true
     */
    @Test
    fun isKeywordMatchExampleTwo() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_name = "Ethereum Classic"), "Classic"), true)
    }

    /**
     * Example 2 - 2
     * input: Classic
     * condition: Tronclassic
     * isMatch: true
     */
    @Test
    fun isKeywordNotMatchExampleTwo() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_name = "Tronclassic"), "Classic"), false)
    }

    /**
     * Example 3
     * input: ET
     * condition: ETH, ETC, ETN
     * isMatch: true
     */
    @Test
    fun isKeywordMatchExampleThree() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_symbol = "ETH"), "ET"), true)
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_symbol = "ETC"), "ET"), true)
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_symbol = "ETN"), "ET"), true)
    }

    /**
     * Example 3 - 2
     * input: ET
     * condition: BET
     * isMatch: false
     */
    @Test
    fun isKeywordNotMatchExampleThree() {
        assertEquals(GetCurrencyMatchedUseCase().invoke(firstExampleCurrency.copy(_symbol = "BET"), "ET"), false)
    }


}