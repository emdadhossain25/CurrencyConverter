package com.example.currencyconverter.currencies.model

data class LatestModel(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: HashMap<String,String>,
    val timestamp: Int
)