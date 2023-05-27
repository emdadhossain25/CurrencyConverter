package com.example.currencyconverter.currencyFeature.usecase

import javax.inject.Inject


class ConversionUseCase @Inject constructor() : IConversionUseCase {
    override suspend fun invoke(
        amount: Double,
        dividerAmount: Double,
        baseAmount: Double,
        base: String
    ): Map<String, String> {
        lateinit var resultMap: Map<String, String>;
        var resultAmount = (baseAmount / dividerAmount) * amount

        resultMap = mapOf<String, String>(base to resultAmount.toString())

        return resultMap
    }

}
