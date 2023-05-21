package com.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.currencyconverter.db.CurrencyConverterDao
import com.example.currencyconverter.db.CurrencyConverterDatabase
import com.example.currencyconverter.di.DBModule
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
object TestDatabaseModule {


    @Singleton
    @Provides
    fun provideTestDB(@ApplicationContext context: Context): CurrencyConverterDatabase {
        return Room.databaseBuilder(
            context,
            CurrencyConverterDatabase::class.java,
            "currency_database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideCurrencyConverterDao(db: CurrencyConverterDatabase): CurrencyConverterDao {
        return db.provideDao()
    }


}