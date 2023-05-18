package com.example.currencyconverter.di

import com.example.currencyconverter.currencies.data.LatestRepository
import com.example.currencyconverter.currencies.data.ILatestRepository
import com.example.currencyconverter.currencies.datasource.ILatestService
import com.example.currencyconverter.currencies.domain.LatestUseCase
import com.example.currencyconverter.currencies.domain.ILatestUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesCurrencyService(retrofit: Retrofit): ILatestService {
        return retrofit.create(ILatestService::class.java)
    }

    @Provides
    @Singleton
    fun provideDispather():CoroutineDispatcher{
        return Dispatchers.IO
    }

    //use interface for instance creation
    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        //for custom class we use binds
        // func (whatwillbeprovided):Whatisaskedfor
        @Binds
        @Singleton
        fun provideLatestRepository(repo: LatestRepository): ILatestRepository

        @Binds
        @Singleton
        fun provideLatestUseCase(latesUseCase: LatestUseCase): ILatestUseCase

    }
}