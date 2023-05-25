package com.example.currencyconverter.currencyFeature.viewmodel

import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.usecase.ILatestUseCase

class FakeLatestUseCase : ILatestUseCase {

    override suspend fun invoke(app_id: String): LatestModel {
        return LatestModel("HUN")
    }

}
