package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.db.CurrencyConverterDao
import com.example.currencyconverter.db.CurrencyConverterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DBModule::class]
)
@Module
class TestDatabaseModule {

    @Provides
    @Singleton
    fun providesDB(@ApplicationContext app: Context): CurrencyConverterDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            CurrencyConverterDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideCurrencyConverterDao(db: CurrencyConverterDatabase): CurrencyConverterDao {
        return db.provideDao()
    }


}