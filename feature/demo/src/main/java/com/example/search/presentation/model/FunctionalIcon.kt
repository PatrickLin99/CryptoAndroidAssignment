package com.example.search.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class FunctionalIcon(@StringRes val title: Int, @DrawableRes val icon: Int) {
    Clear(
        com.example.resource.R.string.FunctionalButton_Clear,
        com.example.resource.R.drawable.ic_clear
    ),
    Fetch(
        com.example.resource.R.string.FunctionalButton_Fetch,
        com.example.resource.R.drawable.ic_load
    ),
    Crypto(
        com.example.resource.R.string.FunctionalButton_Crypto,
        com.example.resource.R.drawable.ic_crypto
    ),
    Fiat(
        com.example.resource.R.string.FunctionalButton_Fiat,
        com.example.resource.R.drawable.ic_fiat
    ),
    All(
        com.example.resource.R.string.FunctionalButton_All,
        com.example.resource.R.drawable.ic_crypto_fiat
    )
}