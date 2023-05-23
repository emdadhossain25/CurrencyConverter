package com.example.currencyconverter.currencyFeature.usecase

import com.example.currencyconverter.currencyFeature.model.LatestModel

interface ILatestUseCase {
    suspend operator fun invoke(app_id: String): LatestModel
}