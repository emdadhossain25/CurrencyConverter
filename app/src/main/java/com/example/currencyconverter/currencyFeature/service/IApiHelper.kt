package com.example.currencyconverter.currencyFeature.service

import com.example.currencyconverter.currencyFeature.model.LatestModel
import kotlinx.coroutines.flow.Flow

interface IApiHelper {
    fun getLatestService(app_id: String): Flow<LatestModel>
}