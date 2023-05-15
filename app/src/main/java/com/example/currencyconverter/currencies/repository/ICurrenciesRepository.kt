package com.example.currencyconverter.currencies.repository

import com.example.currencyconverter.currencies.model.CurrenciesModel

interface ICurrenciesRepository {
    suspend fun getAllCurrencies(app_id: String): CurrenciesModel
}