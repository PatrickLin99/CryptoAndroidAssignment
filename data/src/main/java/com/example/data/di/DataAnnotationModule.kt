package com.example.data.di

import androidx.room.Room
import com.example.data.model.AppDatabase
import com.example.data.repository.CurrencyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

//TODO Patrick 刪？
@Module
@ComponentScan("com.example.data")
class DataAnnotationModule

val dataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    single { get<AppDatabase>().currencyDao() }
    single { CurrencyRepository(get()) }
}