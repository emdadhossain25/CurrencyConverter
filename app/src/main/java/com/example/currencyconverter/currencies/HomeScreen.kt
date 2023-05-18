package com.example.currencyconverter.currencies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencies.viewmodel.HomeViewModel
import com.example.currencyconverter.currencies.viewmodel.ViewState


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

    val viewState by remember { viewModel.viewState }
    when (viewState) {
        is ViewState.Error -> Text(text = (viewState as ViewState.Error).errorMessage)
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Success -> {
            viewModel.saveToDb((viewState as ViewState.Success).data)
//            val data = //TODO query from db
//            val result = data.rates
//            val list: List<String> = result.keys.toList()
//            LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
//                items(list) { item ->
//                    SingleItemCategory(item)
//                }
//            }
        }
    }


}

@Composable
fun SingleItemCategory(item: String) {
    Text(item)
}

