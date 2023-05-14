package com.example.currencyconverter

import com.example.currencyconverter.categories.repository.CurrenciesRepository
import com.example.currencyconverter.categories.repository.ICurrenciesRepository
import com.example.currencyconverter.categories.usecase.CurrenciesUseCase
import com.example.currencyconverter.categories.usecase.ICurrenciesUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //use interface for instance creation
    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {
        //for custom class we use binds
        // func (whatwillbeprovided):Whatisaskedfor
        @Binds
        @Singleton
        fun provideCurrenciesRepository(repo: CurrenciesRepository): ICurrenciesRepository

        @Binds
        @Singleton
        fun provideCurrencyUseCase(currenciesUseCase: CurrenciesUseCase): ICurrenciesUseCase

    }
}