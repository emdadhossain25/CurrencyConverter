package com.example.currencyconverter.currencies.service

import com.example.currencyconverter.currencies.model.LatestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ILatestService {
    @GET("latest.json")
    suspend fun getLatest(
        @Query("app_id") app_id: String
    ): LatestModel
}