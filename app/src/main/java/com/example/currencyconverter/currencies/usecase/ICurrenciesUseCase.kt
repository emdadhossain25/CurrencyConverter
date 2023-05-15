package com.example.currencyconverter.currencies.usecase

import com.example.currencyconverter.currencies.model.CurrenciesModel

interface ICurrenciesUseCase {
    suspend operator fun invoke(app_id: String): CurrenciesModel
}