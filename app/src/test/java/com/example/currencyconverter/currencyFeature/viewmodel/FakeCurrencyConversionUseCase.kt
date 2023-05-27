package com.example.currencyconverter.currencyFeature.viewmodel

import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.usecase.IConversionUseCase
import com.example.currencyconverter.currencyFeature.usecase.ILatestUseCase

class FakeCurrencyConversionUseCase : IConversionUseCase {

    override suspend fun invoke(
        amount: Double,
        dividerAmount: Double,
        baseAmount: Double,
        base: String
    ): Map<String, String> {
        return mapOf<String, String>("USD" to "3.0")
    }

}
