package com.example.search.di

import com.example.data.repository.CurrencyRepository
import com.example.search.domanin.useCase.DeleteAllLocalCurrencyUseCase
import com.example.search.domanin.useCase.GetCurrencyByTypeUseCase
import com.example.search.domanin.useCase.GetCurrencyMatchedUseCase
import com.example.search.domanin.useCase.SetLocalCurrencyUseCase
import com.example.search.presentation.viewModel.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val demoModule = module {
    viewModel {
        DemoViewModel(get(), get(), get(), get())
    }

    single { CurrencyRepository(get()) }
    single { GetCurrencyByTypeUseCase(get()) }
    single { SetLocalCurrencyUseCase(get()) }
    single { DeleteAllLocalCurrencyUseCase(get()) }
    single { GetCurrencyMatchedUseCase() }
}