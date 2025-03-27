package com.example.search.di

import com.example.data.repository.CurrencyRepository
import com.example.search.presentation.viewModel.DemoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
@ComponentScan("com.example.search")
class SearchAnnotationModule

val searchModule = module {
    viewModel {
        DemoViewModel(get())
    }

    single { CurrencyRepository(get()) }
}