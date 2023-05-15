package com.example.currencyconverter.currencies.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ICurrencyService {
    @GET("latest.json")
    suspend fun getCurrencies(
        @Query("app_id") app_id: String
    ): String
}