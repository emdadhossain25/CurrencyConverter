package com.example.currencyconverter.currencies.repository

import com.example.currencyconverter.currencies.service.ICurrencyService
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    val currencyService: ICurrencyService
) : ICurrenciesRepository {
    override suspend fun getAllCurrencies(app_id: String): String {
        return currencyService.getCurrencies(app_id = app_id)
    }
}