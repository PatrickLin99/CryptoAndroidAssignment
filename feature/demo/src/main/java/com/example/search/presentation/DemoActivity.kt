package com.example.search.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.search.presentation.viewModel.DemoViewModel
import com.example.search.presentation.screen.CurrenciesScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity: ComponentActivity() {

    private val vm by viewModel<DemoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrenciesScreen(
                viewInfoState = vm.viewInfo.collectAsStateWithLifecycle(),
                keyword = vm.keyword.collectAsStateWithLifecycle(""),
                onKeywordsChangeListener = ::onKeywordsChangeListener,
                onClickBackListener = { this.finish() },
                onClickCommitSearchListener = ::onCommitSearchListener,
                ::onClickClearKeywordListener,
                ::onClickClearLocalCurrenciesListener,
                ::onClickSetLocalCurrenciesListener,
                ::onFetchLocalCurrencyListener,
                ::onFilterCryptoCurrency,
                ::onFilterFiatCurrency,
            )
        }
    }

    private fun onKeywordsChangeListener(keyword: String) {
        vm.updateKeywords(keyword)
    }

    private fun onClickClearLocalCurrenciesListener() {
        vm.clearLocalCurrencies()
    }

    private fun onClickSetLocalCurrenciesListener() {
        vm.setLocalCurrencies()
    }

    private fun onFetchLocalCurrencyListener() {
        vm.getAllCurrencies()
    }

    private fun onFilterCryptoCurrency() {
        vm.filterCryptoCurrency()
    }

    private fun onFilterFiatCurrency() {
        vm.filterFiatCurrency()
    }

    private fun onCommitSearchListener() {
        vm.commitSearchCondition()
    }

    private fun onClickClearKeywordListener() {
        vm.clearSearchKeyword()
    }
}