package com.example.currencyconverter.currencyFeature.usecase

interface IConversionUseCase {
    suspend operator fun invoke(
        amount: Double,
        dividerAmount: Double,
        baseAmount: Double,
        base: String
    ): Map<String, String>
}