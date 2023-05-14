package com.example.currencyconverter.categories.usecase

interface ICurrenciesUseCase {
    suspend operator fun invoke(app_id: String): String
}