package com.example.currencyconverter.currencies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencies.viewmodel.HomeViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    DisposableEffect(key1 = Unit) {
        if (!(Constants.APP_ID.isNullOrBlank())) {
            viewModel.getCurrencies(Constants.APP_ID)
        }

        onDispose { }
    }

    val result by remember { viewModel.latestModel }
    val list: List<String> = result.keys.toList()




    LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
        items(list) { item ->
            SingleItemCategory(item)
        }
    }


}

@Composable
fun SingleItemCategory(item: String) {
    Text(item)
}

