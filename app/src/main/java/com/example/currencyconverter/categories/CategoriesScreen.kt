package com.example.currencyconverter.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.categories.viewmodel.CurrenciesViewModel
import com.example.currencyconverter.common.Constants

@Composable
fun CategoriesScreen(
    viewModel: CurrenciesViewModel = hiltViewModel()
) {

    DisposableEffect(key1 = Unit) {
        if (!(Constants.APP_ID.isNullOrBlank())) {
            viewModel.getCurrencies(Constants.APP_ID)
        }
        onDispose { }
    }

    Column {
        Text(text = "")
        Spacer(modifier = Modifier.height(30.dp))
    }

}

