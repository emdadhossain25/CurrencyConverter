package com.example.currencyconverter.currencies.usecase

import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.repository.ILatestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LatestUseCase @Inject constructor(
    private val repository: ILatestRepository
) : ILatestUseCase {
    override suspend fun invoke(app_id: String): LatestModel {
        return repository.getLatest(app_id)
    }
}