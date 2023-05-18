package com.example.currencyconverter.currencies.repository

import android.util.Log
import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.service.ILatestService
import com.example.currencyconverter.db.CurrencyConverterDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LatestRepository @Inject constructor(
    val currencyService: ILatestService,
    val dao: CurrencyConverterDao,
    val dispatcher: CoroutineDispatcher
) : ILatestRepository {
    override suspend fun getLatest(app_id: String): LatestModel {
        return withContext(dispatcher) { // moving the operation out of the main layer to IO layer
            val response = try {
                var hold = LatestModel()  //empty response
                //condition to check 30 minutes difference before every call to api
                if ((dao.getLatestInfoDB() == null) || ((FRESH_TIMEOUT - dao.getLatestInfoDB().timestamp) > 1800000)) {
                    hold = currencyService.getLatest(app_id = app_id)
                    dao.saveLatestModel(hold)
                } else {
                    hold = dao.getLatestInfoDB()
                }
                hold
            } catch (e: Exception) {
                Log.d("EH", e.localizedMessage ?: "Unknown Error")
                dao.getLatestInfoDB()
            }
            response
            //returning response from one data source supporting offline

        }


    }

    companion object {
        val FRESH_TIMEOUT = System.currentTimeMillis() / 1000
    }
}