package com.example.currencyconverter.currencies.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest_info")
data class LatestModel(
    @PrimaryKey
    var base: String = "USD",
    val disclaimer: String? = null,
    val license: String? = null,
    val rates: Map<String, String>? = emptyMap(),
    var timestamp: Int = -1
)