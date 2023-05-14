package com.example.currencyconverter.categories.viewmodel

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.categories.usecase.ICurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    val useCase: ICurrenciesUseCase
) : ViewModel() {

    init {
        useCase()
    }
}