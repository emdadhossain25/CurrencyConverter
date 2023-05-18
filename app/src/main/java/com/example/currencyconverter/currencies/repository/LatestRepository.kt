package com.example.currencyconverter.currencies.repository

import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.service.ILatestService
import com.example.currencyconverter.db.CurrencyConverterDao
import javax.inject.Inject

class LatestRepository @Inject constructor(
    val currencyService: ILatestService,
    val dao: CurrencyConverterDao
) : ILatestRepository {
    override suspend fun getLatest(app_id: String): LatestModel {
        val response = currencyService.getLatest(app_id = app_id)
        dao.saveLatestModel(response)
        return response
    }
}