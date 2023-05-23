package com.example.currencyconverter.currencies.repository

import android.util.Log
import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.service.IApiHelper
import com.example.currencyconverter.currencies.service.ILatestService
import com.example.currencyconverter.db.CurrencyConverterDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LatestRepository @Inject constructor(
    val currencyService: IApiHelper,
    val dao: CurrencyConverterDao,
    val dispatcher: CoroutineDispatcher
) : ILatestRepository {
    override suspend fun getLatest(app_id: String): LatestModel {
        return withContext(dispatcher) { // moving the operation out of the main layer to IO layer
            var hold = LatestModel()
            if ((dao.getLatestInfoDB().first() == null) ||
                (dao.getLatestInfoDB().first().timestamp == null) ||
                ((FRESH_TIMEOUT - dao.getLatestInfoDB().first().timestamp!!) > 1800000)
            ) {
                currencyService.getLatestService(app_id = app_id).collect {

                    dao.deleteAll() // delete all previous entries
                    dao.saveLatestModel(it) // insert into db
                    hold = it
                }
                hold
            } else {
                hold = dao.getLatestInfoDB().first()

            }
            hold

        }


    }

    companion object {
        val FRESH_TIMEOUT = System.currentTimeMillis() / 1000
    }
}