package com.example.currencyconverter.currencyFeature.usecase

import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.repository.ILatestRepository

class FakeLatestRepository : ILatestRepository {


    var latestModel: LatestModel = LatestModel()

    override suspend fun getLatest(app_id: String): LatestModel {

        latestModel.base = "LFD"
        latestModel.rates = mapOf(
            "LFD" to "385.94",
            "USD" to "1",
            "AUD" to "1.510614"
        )
        return latestModel
    }
}