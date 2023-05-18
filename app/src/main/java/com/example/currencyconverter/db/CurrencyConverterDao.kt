package com.example.currencyconverter.db

import androidx.room.Dao
import androidx.room.Query
import com.example.currencyconverter.currencies.model.LatestModel

@Dao
interface CurrencyConverterDao {
    @Query("SELECT * from latest_info")
    suspend fun getLatestInfoDB():LatestModel
}