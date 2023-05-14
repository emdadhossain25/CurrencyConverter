package com.example.currencyconverter.categories.repository

interface ICurrenciesRepository {
    suspend fun getAllCurrencies(app_id: String): String
}