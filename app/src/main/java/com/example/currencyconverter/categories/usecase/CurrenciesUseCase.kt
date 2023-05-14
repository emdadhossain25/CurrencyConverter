package com.example.currencyconverter.categories.usecase

import com.example.currencyconverter.categories.repository.ICurrenciesRepository
import javax.inject.Inject

class CurrenciesUseCase @Inject constructor(
    private val repository: ICurrenciesRepository
) : ICurrenciesUseCase {
    override fun invoke(): String {
        return repository.getAllCurrencies()
    }
}