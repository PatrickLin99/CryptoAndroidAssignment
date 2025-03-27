package com.example.cryptoandroidtest

import android.app.Application
import com.example.data.di.dataModule
import com.example.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CryptoAndroidAssignment: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(searchModule, dataModule))
        }
    }
}