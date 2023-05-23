package com.example.currencyconverter.currencyFeature.usecase

import com.example.currencyconverter.currencyFeature.model.LatestModel
import com.example.currencyconverter.currencyFeature.repository.ILatestRepository
import javax.inject.Inject

class LatestUseCase @Inject constructor(
    private val repository: ILatestRepository
) : ILatestUseCase {
    override suspend fun invoke(app_id: String): LatestModel {
        return repository.getLatest(app_id)
    }
}