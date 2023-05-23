package com.example.currencyconverter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyconverter.common.MapTypeConverter
import com.example.currencyconverter.currencyFeature.model.LatestModel

@Database(
    entities = [LatestModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MapTypeConverter::class)
abstract class CurrencyConverterDatabase : RoomDatabase() {
    abstract fun provideDao(): CurrencyConverterDao
}