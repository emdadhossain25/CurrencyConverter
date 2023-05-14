package com.example.currencyconverter.categories.repository

import javax.inject.Inject

class CategoriesRepository @Inject constructor(
//TODO will have to add remote and local datasource
) : ICategoriesRepository {
    override fun getAllCurrencies(): String {
        return "page 42"
    }
}