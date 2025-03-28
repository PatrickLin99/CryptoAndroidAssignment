package com.example.search.domanin.useCase

import com.example.data.repository.CurrencyRepository

class DeleteAllLocalCurrencyUseCase(private val currencyRepository: CurrencyRepository) {
    suspend operator fun invoke() {
        currencyRepository.deleteAllCurrency()
    }
}