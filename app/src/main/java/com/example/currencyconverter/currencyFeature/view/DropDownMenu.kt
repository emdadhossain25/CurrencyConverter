@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.currencyconverter.currencyFeature.view

import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DropDownMenu(
    list: Map<String, String>,
    homeViewModel: HomeViewModel
) {
    val listItems = list
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var currencyCode = homeViewModel.currencyState.collectAsState().value

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = it
        }
    ) {

        TextField(
            value = currencyCode,
            onValueChange = {
//                homeViewModel::setCurrency
            },
            textStyle = TextStyle.Default.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            ),
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
            listItems.forEach { itemValue ->
                DropdownMenuItem(

                    text = {
                        Text(
                            modifier = Modifier
                                .width(70.dp),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            text = itemValue.key,
                        )
                    },
                    onClick = {
                        homeViewModel.setCurrency(itemValue.key)
                        homeViewModel.setDividerAmount(itemValue.value)
                        isExpanded = false
                    })


            }
        }
    }


}



























