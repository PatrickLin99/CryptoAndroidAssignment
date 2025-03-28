package com.example.search.presentation.model

data class CurrencyScreenListener(
    val onKeywordsChangeListener: (String) -> Unit,
    val onClickBackListener: () -> Unit,
    val onClickCommitSearchListener: () -> Unit,
    val onClickClearKeywordListener: () -> Unit,
    val onClickClearLocalCurrenciesListener: () -> Unit,
    val onClickSetLocalCurrenciesListener: () -> Unit,
    val onFetchLocalCurrencyListener: () -> Unit,
    val onFilterCryptoCurrency: () -> Unit,
    val onFilterFiatCurrency: () -> Unit,
)
