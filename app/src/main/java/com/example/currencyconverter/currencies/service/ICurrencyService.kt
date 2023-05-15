package com.example.currencyconverter.currencies.service

import com.example.currencyconverter.currencies.model.CurrenciesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrencyService {
    @GET("currencies.json")
    suspend fun getCurrencies(
        @Query("app_id") app_id: String
    ): CurrenciesModel
}