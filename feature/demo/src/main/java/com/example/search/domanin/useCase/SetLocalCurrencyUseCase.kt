package com.example.search.domanin.useCase

import com.example.data.model.CurrencyInfo
import com.example.data.repository.CurrencyRepository

class SetLocalCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke(currencies: List<CurrencyInfo>) {
        currencies.map {
            currencyRepository.setLocalCurrency(it)
        }
    }
}