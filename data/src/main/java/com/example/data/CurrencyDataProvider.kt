package com.example.data

import com.example.data.model.CurrencyInfo

object CurrencyDataProvider {
    val currencies = listOf(
        /**
         * List A - Crypto
         */
        CurrencyInfo(
            id = "BTC",
            _name = "Bitcoin",
            _symbol = "BTC",
            code = null
        ),
        CurrencyInfo(
            id = "ETH",
            _name = "Ethereum",
            _symbol = "ETH",
            code = null
        ),
        CurrencyInfo(
            id = "XRP",
            _name = "XRP",
            _symbol = "XRP",
            code = null
        ),
        CurrencyInfo(
            id = "BCH",
            _name = "Bitcoin Cash",
            _symbol = "BCH",
            code = null
        ),
        CurrencyInfo(
            id = "LTC",
            _name = "Litecoin",
            _symbol = "LTC",
            code = null
        ),
        CurrencyInfo(
            id = "EOS",
            _name = "EOS",
            _symbol = "EOS",
            code = null
        ),
        CurrencyInfo(
            id = "BNB",
            _name = "Binance Coin",
            _symbol = "BNB",
            code = null
        ),
        CurrencyInfo(
            id = "LINK",
            _name = "Chainlink",
            _symbol = "LINK",
            code = null
        ),
        CurrencyInfo(
            id = "NEO",
            _name = "NEO",
            _symbol = "NEO",
            code = null
        ),
        CurrencyInfo(
            id = "ETC",
            _name = "Ethereum Classic",
            _symbol = "ETC",
            code = null
        ),
        CurrencyInfo(
            id = "ONT",
            _name = "Ontology",
            _symbol = "ONT",
            code = null
        ),
        CurrencyInfo(
            id = "CRO",
            _name = "Crypto.com Chain",
            _symbol = "CRO",
            code = null
        ),
        CurrencyInfo(
            id = "CUC",
            _name = "Cucumber",
            _symbol = "CUC",
            code = null
        ),
        CurrencyInfo(
            id = "USDC",
            _name = "USD Coin",
            _symbol = "USDC",
            code = null
        ),
        /**
         * List B - Fiat
         */
        CurrencyInfo(
            id = "SGD",
            _name = "Singapore Dollar",
            _symbol = "$",
            code = "SGD"
        ),
        CurrencyInfo(
            id = "EUR",
            _name = "Euro",
            _symbol = "€",
            code = "EUR"
        ),
        CurrencyInfo(
            id = "GBP",
            _name = "British Pound",
            _symbol = "£",
            code = "GBP"
        ),
        CurrencyInfo(
            id = "HKD",
            _name = "Hong Kong Dollar",
            _symbol = "$",
            code = "HKD"
        ),
        CurrencyInfo(
            id = "JPY",
            _name = "Japanese Yen",
            _symbol = "¥",
            code = "JPY"
        ),
        CurrencyInfo(
            id = "AUD",
            _name = "Australian Dollar",
            _symbol = "$",
            code = "AUD"
        ),
        CurrencyInfo(
            id = "USD",
            _name = "United States Dollar",
            _symbol = "$",
            code = "USD"
        ),
    )
}