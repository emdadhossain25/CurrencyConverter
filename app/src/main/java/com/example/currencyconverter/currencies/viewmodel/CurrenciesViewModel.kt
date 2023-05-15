package com.example.currencyconverter.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            try {
                val currenciesResponse = useCase(app_id)
                Log.d("success", currenciesResponse ?: "Unknown Error Message")
            } catch (e: Exception) {
                Log.d("Error", e.message ?: "Unknown Error Message")
            }
        }
    }
}