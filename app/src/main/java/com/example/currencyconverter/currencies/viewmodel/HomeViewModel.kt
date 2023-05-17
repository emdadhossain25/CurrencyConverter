package com.example.currencyconverter.currencies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.currencies.model.LatestModel
import com.example.currencyconverter.currencies.usecase.ILatestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val useCase: ILatestUseCase
) : ViewModel() {
    fun getCurrencies(app_id: String) {
        viewModelScope.launch {
            var latestModel: LatestModel = useCase(app_id)
            latestModel.rates.forEach {
                Log.d("key", it.key)
                Log.d("value", ""+it.value)
            }

        }
    }
}