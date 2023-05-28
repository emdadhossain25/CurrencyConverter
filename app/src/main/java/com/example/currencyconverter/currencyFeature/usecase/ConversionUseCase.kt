package com.example.currencyconverter.currencyFeature.usecase

import javax.inject.Inject


class ConversionUseCase @Inject constructor() : IConversionUseCase {
    override suspend fun invoke(
        multiplierAmount: Double,
        dividerAmount: Double,
        baseAmountToBeDivided: Double,
        baseCurrencyString: String
    ): Map<String, String> {
        lateinit var resultMap: Map<String, String>;
        var resultAmount = (baseAmountToBeDivided / dividerAmount) * multiplierAmount

        resultMap = mapOf<String, String>(baseCurrencyString to resultAmount.toString())

        return resultMap
    }

}
