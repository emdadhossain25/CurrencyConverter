package com.example.currencyconverter.currencyFeature

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.currencyconverter.common.Constants
import com.example.currencyconverter.currencyFeature.view.DropDownMenu
import com.example.currencyconverter.currencyFeature.view.HomeViewModel
import com.example.currencyconverter.currencyFeature.view.AmountInputTextField
import com.example.currencyconverter.utils.ViewState


@Composable
fun CategoriesScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewStateObject by viewModel.viewState.observeAsState()
    val conversionStateObject by viewModel.viewStateForConversion.observeAsState()

    DisposableEffect(key1 = Unit) {
        if (!(Constants.APP_ID.isNullOrBlank())) {
            viewModel.getCurrencies(Constants.APP_ID)
        }

        onDispose { }
    }


    var modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)

    Column(
        modifier = modifier,
    ) {

        when (viewStateObject) {


            is ViewState.Error -> Text(
                modifier = modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,

                text = (viewStateObject as ViewState.Error).errorMessage
            )

            ViewState.Loading -> Text(text = "Loading")

            is ViewState.Success -> {
                val data = (viewStateObject as ViewState.Success).data
                val result = data.rates
                val list: List<String> = result?.keys?.toList() ?: emptyList()


                AmountInputTextField(viewModel)


                DropDownForCurrencySelector(
                    list,
                    modifier,
                    viewModel
                )

                if (
                    viewModel.amountState.collectAsState().value?.isNotEmpty() == true &&
                    viewModel.currencyState.collectAsState().value?.isNotEmpty() == true
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        items(result?.toList() ?: emptyList()) { item ->

                            SingleItemCategory(item)
                        }
                    }
                }


            }
            null -> {
                Text(text = (viewStateObject as ViewState.Error).errorMessage)
            }
        }
    }


}

@Composable
fun SingleItemCategory(item: Pair<String, String>) {
    val paddingModifier = Modifier.padding(10.dp)

    Card(
        modifier = paddingModifier
            .width(60.dp)
            .height(80.dp),
    ) {
        Column(modifier = paddingModifier) {
            Text(
                text = item.first,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = item.second,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,

                )
        }

    }
}

@Composable
fun DropDownForCurrencySelector(
    list: List<String>,
    boxScope: Modifier,
    viewModel: HomeViewModel
) {
    Box(
        modifier = boxScope

    ) {
        DropDownMenu(
            list = list,
            homeViewModel = viewModel
        )

    }
}



