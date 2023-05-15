package com.example.currencyconverter.currencies.usecase

import com.example.currencyconverter.currencies.repository.ICurrenciesRepository
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(
    private val repository: ICurrenciesRepository
) : ICurrenciesUseCase {
    override suspend fun invoke(app_id:String): String {
        return repository.getAllCurrencies(app_id)
    }
}