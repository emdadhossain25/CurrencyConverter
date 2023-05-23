package com.example.currencyconverter.currencies.service

import com.example.currencyconverter.currencies.model.LatestModel
import kotlinx.coroutines.flow.Flow

interface IApiHelper {
    fun getLatestService(app_id: String): Flow<LatestModel>
}