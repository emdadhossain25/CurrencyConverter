package com.example.currencyconverter.categories.usecase

import com.example.currencyconverter.categories.repository.ICategoriesRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository: ICategoriesRepository
) : ICategoryUseCase {
    override fun invoke(): String {
        return repository.getAllCurrencies()
    }
}