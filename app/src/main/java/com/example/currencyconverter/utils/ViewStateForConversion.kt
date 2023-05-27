package com.example.currencyconverter.utils

import com.example.currencyconverter.currencyFeature.model.LatestModel


sealed class ViewStateForConversion {
    object Loading : ViewStateForConversion()
    data class Success(val data: Map<String, String>) : ViewStateForConversion()
    data class Error(val errorMessage: String) : ViewStateForConversion()
}

