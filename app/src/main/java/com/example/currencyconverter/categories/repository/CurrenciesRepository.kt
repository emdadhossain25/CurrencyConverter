package com.example.currencyconverter.categories.repository

import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
//TODO will have to add remote and local datasource
) : ICurrenciesRepository {
    override fun getAllCurrencies(): String {
        return "page 42"
    }
}