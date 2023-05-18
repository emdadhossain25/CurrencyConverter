package com.example.currencyconverter.currencies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest_info")
data class LatestModel(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Map<String, String>,
    @PrimaryKey
    val timestamp: Int
)