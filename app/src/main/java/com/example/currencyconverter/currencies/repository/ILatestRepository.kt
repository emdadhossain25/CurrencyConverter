package com.example.currencyconverter.currencies.repository

import com.example.currencyconverter.currencies.model.LatestModel
import kotlinx.coroutines.flow.Flow

interface ILatestRepository {
    suspend fun getLatest(app_id: String): LatestModel
}