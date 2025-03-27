package com.example.search.presentation.model

import com.example.data.model.CurrencyInfo

data class ViewInfo(
    val isLoading: Boolean = false,
    val isError: ErrorState? = null,
    val currencies: List<CurrencyInfo> = emptyList(),
    val searchResult: List<CurrencyInfo> = emptyList(),
    val isCommit: Boolean? = null
)

enum class ErrorState {
    NOT_MATCHED, EMPTY
}
