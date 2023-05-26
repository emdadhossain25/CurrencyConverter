package com.example.currencyconverter.currencyFeature.view

import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import org.w3c.dom.Text

@Composable
fun OutLineTextFieldSample() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { },
        onValueChange = {
            text = it
        }
    )
}