package com.example.currencyconverter.currencies.usecase

interface ICurrenciesUseCase {
    suspend operator fun invoke(app_id: String): String
}