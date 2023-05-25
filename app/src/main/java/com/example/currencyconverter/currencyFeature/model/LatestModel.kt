package com.example.currencyconverter.currencyFeature.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "latest_info")
data class LatestModel(
    @PrimaryKey
    var base: String = "USD",
    val disclaimer: String? = null,
    var license: String? = null,
    var rates: Map<String, String>? = emptyMap(),
    var timestamp: Int? = -1
)