package com.example.currencyconverter.currencyFeature.view

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*

@Composable
fun AmountInputTextField(viewModel: HomeViewModel) {
    val amount = viewModel.amountState.collectAsState().value
    OutlinedTextField(
        value = amount,
        onValueChange = viewModel::setAmount
    )
}