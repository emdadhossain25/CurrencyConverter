package com.example.currencyconverter.currencyFeature.service

import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiHelper
@Inject constructor(
    val latestService: ILatestService
) : IApiHelper {
    override fun getLatestService(app_id: String) = flow {
        emit(latestService.getLatest(app_id))
    }

}