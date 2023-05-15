package com.example.currencyconverter.currencies.repository

interface ICurrenciesRepository {
    suspend fun getAllCurrencies(app_id: String): String
}