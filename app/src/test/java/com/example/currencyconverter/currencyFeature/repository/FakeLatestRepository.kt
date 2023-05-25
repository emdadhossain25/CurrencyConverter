package com.example.currencyconverter.currencyFeature.repository

import com.example.currencyconverter.currencyFeature.model.LatestModel

class FakeLatestRepository : ILatestRepository {


    var latestModel: LatestModel = LatestModel()

    override suspend fun getLatest(app_id: String): LatestModel {

        latestModel.base = "LFD"
        latestModel.rates=mapOf("LFD" to "385.94")
        return latestModel
    }
}