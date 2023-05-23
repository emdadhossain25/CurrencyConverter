package com.example.currencyconverter.currencyFeature.service

import com.example.currencyconverter.currencyFeature.model.LatestModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IApiHelper {
    fun getLatestService(app_id: String): Flow<Response<LatestModel>>
}