package com.example.currencyconverter.currencies.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _latestModelRates: MutableState<Map<String, String>> =
        mutableStateOf(emptyMap<String, String>())

    val latestModel: State<Map<String, String>> = _latestModelRates
    fun getCurrencies(app_id: String) {

        viewModelScope.launch {
            var latestModel: LatestModel = useCase(app_id)
            _latestModelRates.value = latestModel.rates
        }
    }
}