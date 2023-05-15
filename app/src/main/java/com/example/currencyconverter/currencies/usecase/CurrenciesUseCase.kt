package com.example.currencyconverter.currencies.usecase

import com.example.currencyconverter.currencies.model.CurrenciesModel
import com.example.currencyconverter.currencies.repository.ICurrenciesRepository
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(
    private val repository: ICurrenciesRepository
) : ICurrenciesUseCase {
    override suspend fun invoke(app_id: String): CurrenciesModel {
        return repository.getAllCurrencies(app_id)
    }
}