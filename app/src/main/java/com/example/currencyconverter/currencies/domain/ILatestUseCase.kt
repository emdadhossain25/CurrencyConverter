package com.example.currencyconverter.currencies.domain

import com.example.currencyconverter.currencies.model.LatestModel

interface ILatestUseCase {
    suspend operator fun invoke(app_id: String): LatestModel
}