package com.example.currencyconverter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.currencyconverter.currencyFeature.model.LatestModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyConverterDao {
    @Query("SELECT * from latest_info")
     fun getLatestInfoDB(): Flow<LatestModel>

    @Insert(onConflict = REPLACE)
    suspend fun saveLatestModel(model: LatestModel)

    @Query("DELETE FROM latest_info")
    suspend fun deleteAll()
}