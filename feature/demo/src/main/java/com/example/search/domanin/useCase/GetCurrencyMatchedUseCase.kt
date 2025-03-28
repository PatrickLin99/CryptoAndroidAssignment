package com.example.search.domanin.useCase

import com.example.data.model.CurrencyInfo

class GetCurrencyMatchedUseCase {

    operator fun invoke(currency: CurrencyInfo, keyword: String): Boolean =
        currency.name.startsWith(keyword, true)
            || currency.name.split(" ").any { it.startsWith(keyword) }
            || currency.symbol.startsWith(keyword)
}