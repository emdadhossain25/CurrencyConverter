@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.currencyconverter.currencyFeature.view

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenu(list: List<String>) {
    val listItems = list
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var currencyCode by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }
    ) {

        TextField(
            value = currencyCode,
            onValueChange = {},
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },

            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .width(100.dp)


        )


        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            listItems.forEachIndexed { itemIndex, itemValue ->
                DropdownMenuItem(

                    text = {
                        Text(
                            modifier = Modifier
                                .width(70.dp),
                            textAlign = TextAlign.Center,
                            text = itemValue,
                        )
                    },
                    onClick = {
                        currencyCode = itemValue
                        isExpanded = false
                    })


            }
        }
    }


}



























