package com.example.search.domanin.useCase

import com.example.data.model.CurrencyInfo
import com.example.data.repository.CurrencyRepository
import com.example.search.presentation.model.CurrencyFilterType
import kotlinx.coroutines.flow.Flow

class GetCurrencyByTypeUseCase(private val currencyRepository: CurrencyRepository) {

    operator fun invoke(type: CurrencyFilterType): Flow<List<CurrencyInfo>> {
       return when (type) {
            CurrencyFilterType.ALL -> currencyRepository.getAllCurrencies()
            CurrencyFilterType.Crypto -> currencyRepository.getCrypto()
            CurrencyFilterType.Fiat -> currencyRepository.getFiat()
        }
    }
}