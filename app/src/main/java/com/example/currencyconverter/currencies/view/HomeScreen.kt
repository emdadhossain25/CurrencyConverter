package com.example.currencyconverter.currencies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencies.view.HomeViewModel
import com.example.currencyconverter.utils.ViewState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewStateObject by viewModel.viewState.observeAsState()

    DisposableEffect(key1 = Unit) {
        if (!(Constants.APP_ID.isNullOrBlank())) {
            viewModel.getCurrencies(Constants.APP_ID)
        }

        onDispose { }
    }


    when (viewStateObject) {

        is ViewState.Error -> Text(text = (viewStateObject as ViewState.Error).errorMessage)

        ViewState.Loading -> Text(text = "Loading")

        is ViewState.Success -> {
            val data = (viewStateObject as ViewState.Success).data
            val result = data.rates
            val list: List<String> = result?.keys?.toList() ?: emptyList()
            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
                items(list) { item ->
                    SingleItemCategory(item)
                }
            }
        }
        null -> {
            Text(text = (viewStateObject as ViewState.Error).errorMessage)
        }
    }


}

@Composable
fun SingleItemCategory(item: String) {
    Text(item)
}

