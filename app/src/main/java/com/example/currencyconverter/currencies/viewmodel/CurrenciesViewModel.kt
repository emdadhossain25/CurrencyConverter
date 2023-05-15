package com.example.currencyconverter.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.currencies.model.CurrenciesModel
import com.example.currencyconverter.currencies.usecase.ICurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    val useCase: ICurrenciesUseCase
) : ViewModel() {
    fun getCurrencies(app_id: String) {
        viewModelScope.launch {
            var currenciesModel: CurrenciesModel = useCase(app_id)
            Log.d("getCurrencies", currenciesModel.AED)

        }
    }
}