package com.example.search.di

import com.example.data.repository.CurrencyRepository
import com.example.search.presentation.viewModel.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val demoModule = module {
    viewModel {
        DemoViewModel(get())
    }

    single { CurrencyRepository(get()) }
}