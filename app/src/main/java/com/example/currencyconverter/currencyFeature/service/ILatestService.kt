package com.example.currencyconverter.currencyFeature.service

import com.example.currencyconverter.currencyFeature.model.LatestModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ILatestService {
    @GET("latest.json")
    suspend fun getLatest(
        @Query("app_id") app_id: String
    ): LatestModel
}