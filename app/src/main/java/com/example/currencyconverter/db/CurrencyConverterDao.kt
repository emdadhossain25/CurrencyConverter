package com.example.currencyconverter.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.currencyconverter.currencies.model.LatestModel

@Dao
interface CurrencyConverterDao {
    @Query("SELECT * from latest_info")
    suspend fun getLatestInfoDB(): LatestModel

    @Insert(onConflict = REPLACE)
    suspend fun saveLatestModel(model: LatestModel)

    @Query("DELETE FROM latest_info")
    suspend fun deleteAll()
}