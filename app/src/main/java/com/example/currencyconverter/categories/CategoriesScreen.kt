package com.example.currencyconverter.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesScreen() {
    val currencies = getFakeData()
    Column {
        Text(text = "Initial Setup done")
        Spacer(modifier = Modifier.height(30.dp))
        currencies.forEach {
            Text(text = it)
        }
    }
}

fun getFakeData(): List<String> {
    return listOf(
        "USD",
        "GBP",
        "JY"
    )
}
