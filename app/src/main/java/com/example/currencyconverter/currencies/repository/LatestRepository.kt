package com.example.currencyconverter.currencies.repository

import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.service.ILatestService
import javax.inject.Inject

class LatestRepository @Inject constructor(
    val currencyService: ILatestService
) : ILatestRepository {
    override suspend fun getLatest(app_id: String): LatestModel {
        return currencyService.getLatest(app_id = app_id)
    }
}