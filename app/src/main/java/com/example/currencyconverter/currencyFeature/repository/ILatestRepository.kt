package com.example.currencyconverter.currencyFeature.repository

import com.example.currencyconverter.currencyFeature.model.LatestModel

interface ILatestRepository {
    suspend fun getLatest(app_id: String): LatestModel
}