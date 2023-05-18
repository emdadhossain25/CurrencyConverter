package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.db.CurrencyConverterDao
import com.example.currencyconverter.db.CurrencyConverterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun providesDB(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        CurrencyConverterDatabase::class.java,
        "currency_database"
    ).build()

    @Provides
    @Singleton
    fun provideCurrencyConverterDao(db: CurrencyConverterDatabase): CurrencyConverterDao {
        return db.provideDao()
    }
}