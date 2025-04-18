package com.example.search.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CurrencyDataProvider
import com.example.search.domanin.useCase.DeleteAllLocalCurrencyUseCase
import com.example.search.domanin.useCase.GetCurrencyByTypeUseCase
import com.example.search.domanin.useCase.GetCurrencyMatchedUseCase
import com.example.search.domanin.useCase.SetLocalCurrencyUseCase
import com.example.search.presentation.model.CurrencyFilterType
import com.example.search.presentation.model.ErrorState
import com.example.search.presentation.model.ViewInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DemoViewModel(
    private val setLocalCurrencyUseCase: SetLocalCurrencyUseCase,
    private val getCurrencyByTypeUseCase : GetCurrencyByTypeUseCase,
    private val deleteAllLocalCurrencyUseCase: DeleteAllLocalCurrencyUseCase,
    private val getCurrencyMatchedUseCase: GetCurrencyMatchedUseCase,
): ViewModel() {

    private val _viewInfo: MutableStateFlow<ViewInfo> = MutableStateFlow(ViewInfo())
    val viewInfo = _viewInfo.asStateFlow()

    private val _keyword: MutableSharedFlow<String> = MutableSharedFlow()
    val keyword = _keyword.asSharedFlow()

    private val currencyType = MutableStateFlow(CurrencyFilterType.ALL)
    @OptIn(ExperimentalCoroutinesApi::class)
    val currencies = this.currencyType.flatMapLatest { type ->
        getCurrencyByTypeUseCase.invoke(type)
    }

    init {
        observeInputCondition()
        initCurrenciesObserver()
    }

    fun setLocalCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewInfo.emit(viewInfo.value.copy(isLoading = true))
            setLocalCurrencyUseCase.invoke(CurrencyDataProvider.currencies)
            this@DemoViewModel.currencyType.emit(CurrencyFilterType.ALL)
        }
    }

    fun getAllCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            val currencies = viewInfo.value.currencies
            if (currencies.isEmpty() || currencies.none { it.code == null } || currencies.none { it.code != null }) {
                setLocalCurrencies()
            }
            this@DemoViewModel.currencyType.emit(CurrencyFilterType.ALL)
        }
    }

    fun commitSearchCondition() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewInfo.emit(viewInfo.value.copy(isCommit = true))
        }
    }

    fun clearSearchKeyword() {
        viewModelScope.launch(Dispatchers.IO) {
            _keyword.emit("")
            _viewInfo.emit(viewInfo.value.copy(isCommit = null, isError = null))
        }
    }

    fun updateKeywords(keyword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _viewInfo.emit(viewInfo.value.copy(isCommit = null))
            _keyword.emit(keyword)
        }
    }

    fun clearLocalCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllLocalCurrencyUseCase.invoke()
        }
    }

    fun filterCryptoCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            if (viewInfo.value.currencies.none { it.code == null }) {
                setLocalCurrencyUseCase.invoke(CurrencyDataProvider.currencies.filter { it.code == null })
            }
            this@DemoViewModel.currencyType.emit(CurrencyFilterType.Crypto)
        }
    }

    fun filterFiatCurrency() {
        viewModelScope.launch(Dispatchers.IO) {
            if (viewInfo.value.currencies.none { it.code != null }) {
                setLocalCurrencyUseCase.invoke(CurrencyDataProvider.currencies.filter { it.code != null })
            }
            this@DemoViewModel.currencyType.emit(CurrencyFilterType.Fiat)
        }
    }

    private fun initCurrenciesObserver() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewInfo.emit(viewInfo.value.copy(isLoading = true))
            this@DemoViewModel.currencies.stateIn(this).collect {
                val error = if (it.isEmpty()) ErrorState.EMPTY else null
                _viewInfo.emit(
                    viewInfo.value.copy(
                        isLoading = false,
                        currencies = it,
                        isError = error
                    )
                )
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun observeInputCondition() {
        viewModelScope.launch(Dispatchers.IO) {
            keyword.debounce(500L).distinctUntilChanged().collect { keywords ->
                val previewRes = viewInfo.value.currencies.filter {
                    getCurrencyMatchedUseCase.invoke(it, keywords)
                }
                if (previewRes.isNotEmpty()) {
                    _viewInfo.emit(viewInfo.value.copy(searchResult = previewRes, isError = null))
                } else if (keywords.isEmpty()) {
                    _viewInfo.emit(viewInfo.value.copy(searchResult = emptyList(), isError = null, isCommit = null))
                } else if (previewRes.isEmpty()) {
                    _viewInfo.emit(viewInfo.value.copy(searchResult = emptyList(), isError = ErrorState.NOT_MATCHED))
                }
            }
        }
    }
}