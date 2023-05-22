package com.example.currencyconverter.utils

import com.example.currencyconverter.currencies.model.LatestModel


sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: LatestModel) : ViewState()
    data class Error(val errorMessage: String) : ViewState()
}

